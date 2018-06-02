package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.quizujemy.back.exceptions.ResourceNotFoundException;
import pl.quizujemy.back.models.Question;
import pl.quizujemy.back.repositories.QuestionRepository;
import pl.quizujemy.back.repositories.QuizRepository;

import javax.validation.Valid;

@RestController
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuizRepository quizRepository;

    @GetMapping("quizes/{idquiz}/questions")
    public Iterable<Question> getAllQuestionsByQuizId(@PathVariable(value = "idquiz") Long idquiz){
        return questionRepository.findByQuizId(idquiz);
    }

    @PostMapping("quizes/{idquiz}/questions")
    public Question createQuestion(@PathVariable (value = "idquiz") Long idquiz, @Valid @RequestBody Question question){
        return quizRepository.findById(idquiz).map(quiz -> {
            question.setQuiz(quiz);
            return questionRepository.save(question);
        }).orElseThrow(() -> new ResourceNotFoundException("idarticle " + idquiz + " not found"));
    }

}
