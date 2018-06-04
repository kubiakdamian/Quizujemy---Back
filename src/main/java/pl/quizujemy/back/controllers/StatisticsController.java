package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.quizujemy.back.exceptions.ResourceNotFoundException;
import pl.quizujemy.back.models.Statistics;
import pl.quizujemy.back.repositories.StatisticsRepository;
import pl.quizujemy.back.repositories.UserRepository;

import javax.validation.Valid;

@RestController
public class StatisticsController {
    @Autowired
    StatisticsRepository statisticsRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("user/{iduser}/statistics")
    public Iterable<Statistics> getStatisticsByUserId(@PathVariable(value = "iduser") Long iduser){
        return statisticsRepository.findByUserId(iduser);
    }

    @PostMapping("user/{iduser}/statistics")
    @CrossOrigin(origins = "*")
    public Statistics createStatistics(@PathVariable (value = "iduser") Long iduser, @Valid @RequestBody Statistics statistics){
        return userRepository.findById(iduser).map(user -> {
            statistics.setUser(user);
            statistics.setAnsweredQuestions(0);
            statistics.setCorrectAnswers(0);
            return statisticsRepository.save(statistics);
        }).orElseThrow(() -> new ResourceNotFoundException("iduser " + iduser + " not found"));
    }

    @PutMapping("/statistics/{idstatistics}")
    @CrossOrigin(origins = "*")
    public Statistics updateArticle(@PathVariable Long idstatistics, @Valid @RequestBody Statistics statisticsRequest){
        return statisticsRepository.findById(idstatistics).map(statistics -> {
            statistics.setCorrectAnswers(statisticsRequest.getCorrectAnswers());
            statistics.setAnsweredQuestions(statisticsRequest.getAnsweredQuestions());
            return statisticsRepository.save(statistics);
        }).orElseThrow(() -> new ResourceNotFoundException("idarticle " + idstatistics + " not found"));
    }
}
