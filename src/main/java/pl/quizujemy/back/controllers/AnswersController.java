package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.quizujemy.back.exceptions.ResourceNotFoundException;
import pl.quizujemy.back.models.Answers;
import pl.quizujemy.back.models.Question;
import pl.quizujemy.back.repositories.AnswersRepository;
import pl.quizujemy.back.repositories.QuestionRepository;

import javax.validation.Valid;

@RestController
public class AnswersController {
    @Autowired
    AnswersRepository answersRepository;

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("questions/{idquestion}/answers")
    public Iterable<Answers> getAllAnswersByQuestionId(@PathVariable(value = "idquestion") Long idquestion){
        return answersRepository.findByQuestionId(idquestion);
    }

    @PostMapping("questions/{idquestion}/answers")
    public Answers createAnswers(@PathVariable (value = "idquestion") Long idquestion, @Valid @RequestBody Answers answers){
        return questionRepository.findById(idquestion).map(question -> {
            answers.setQuestion(question);
            return answersRepository.save(answers);
        }).orElseThrow(() -> new ResourceNotFoundException("idarticle " + idquestion + " not found"));
    }
}
