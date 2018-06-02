package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.quizujemy.back.models.Quiz;
import pl.quizujemy.back.repositories.QuizRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class QuizController {
    @Autowired
    QuizRepository quizRepository;

    @GetMapping("/quizes")
    public @ResponseBody
    Iterable<Quiz> getAllQuizes(){
        return quizRepository.findAll();
    }

    @GetMapping("/quizes/{idquiz}")
    public @ResponseBody Optional<Quiz> getQuizById(@PathVariable(value = "idquiz") Long idquiz){
        return quizRepository.findById(idquiz);
    }

    @PostMapping("/quizes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
