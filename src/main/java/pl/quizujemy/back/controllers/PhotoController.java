package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.quizujemy.back.exceptions.ResourceNotFoundException;
import pl.quizujemy.back.models.Photo;
import pl.quizujemy.back.repositories.PhotoRepository;
import pl.quizujemy.back.repositories.UserRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class PhotoController {
    private String UPLOAD_DIR = ""; // Path to Uploaded images

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("user/{iduser}/photo")
    public Iterable<Photo> getPhotoByUserId(@PathVariable(value = "iduser") Long iduser){
        return photoRepository.findByUserId(iduser);
    }

    @PostMapping("user/{iduser}/photo")
    @CrossOrigin(origins = "*")
    public Photo createPhoto(@PathVariable (value = "iduser") Long iduser, @Valid @RequestBody Photo photo){
        return userRepository.findById(iduser).map(user -> {
            photo.setUser(user);
            photo.setPhoto("/default_user.png");
            return photoRepository.save(photo);
        }).orElseThrow(() -> new ResourceNotFoundException("iduser " + iduser + " not found"));
    }

    @PutMapping("/photo/{idphoto}")
    @CrossOrigin(origins = "*")
    public Photo uploadPhoto(@PathVariable Long idphoto,
                                  @RequestParam("file") MultipartFile file)throws IOException {
        String path = "/" + saveUploadedFile(file);
        return photoRepository.findById(idphoto).map(photo -> {
            photo.setPhoto(path);
            return photoRepository.save(photo);
        }).orElseThrow(() -> new ResourceNotFoundException("idarticle " + idphoto + " not found"));
    }

    private String saveUploadedFile(MultipartFile file)throws IOException {
        byte[] bytes = file.getBytes();
        String fileName = UUID.randomUUID().toString();
        Path path = Paths.get(UPLOAD_DIR, fileName);
        Files.write(path, bytes);

        return fileName;
    }
}
