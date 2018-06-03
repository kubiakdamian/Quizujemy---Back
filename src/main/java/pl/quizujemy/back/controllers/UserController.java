package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import pl.quizujemy.back.exceptions.ResourceNotFoundException;
import pl.quizujemy.back.models.User;
import pl.quizujemy.back.repositories.UserRepository;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @CrossOrigin(origins = "*")
    public String registerNewUserAccount(@Valid @RequestBody User user) {
        if (emailExist(user.getEmail())) {
            throw new ResourceNotFoundException("There is an account with that email address: " + user.getEmail());
        }
        final User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        newUser.setRole(user.getRole());
        userRepository.save(newUser);

        return "Success";
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public User login(@Valid @RequestBody User user) {
        String response;
        if (!emailExist(user.getEmail())) {
            throw new ResourceNotFoundException("No such email");
        }else{
            System.out.println(user.getPassword());
            System.out.println(getUserByEmail(user.getEmail()).getPassword());
            if (BCrypt.checkpw(user.getPassword(), getUserByEmail(user.getEmail()).getPassword())){
                response = "It matches";
            }else{
                throw new ResourceNotFoundException("Wrong password");
            }
        }

        return getUserByEmail(user.getEmail());
    }


    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    private User getUserByEmail(final String email){
        return userRepository.findByEmail(email);
    }

    private boolean emailExist(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
