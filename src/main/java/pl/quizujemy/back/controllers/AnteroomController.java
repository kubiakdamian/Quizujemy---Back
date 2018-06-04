package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.quizujemy.back.exceptions.ResourceNotFoundException;
import pl.quizujemy.back.models.Anteroom;
import pl.quizujemy.back.repositories.AnteroomRepository;

import javax.validation.Valid;

@RestController
public class AnteroomController {
    @Autowired
    private AnteroomRepository anteroomRepository;

    @PostMapping("/anteroom/add")
    @CrossOrigin(origins = "*")
    public Anteroom createArticle(@Valid @RequestBody Anteroom anteroom){
        return anteroomRepository.save(anteroom);
    }

    @GetMapping(path = "/anteroom/curiosities")
    public @ResponseBody Iterable<Anteroom> getAllAnterooms(){
        return anteroomRepository.findAll();
    }

    @DeleteMapping("anteroom/delete/{idanteroom}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteAnteroom(@PathVariable Long idanteroom) {
        return anteroomRepository.findById(idanteroom).map(anteroom -> {
            anteroomRepository.delete(anteroom);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("idarticle " + idanteroom + " not found"));
    }
}
