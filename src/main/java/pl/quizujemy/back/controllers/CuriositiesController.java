package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.quizujemy.back.models.Anteroom;
import pl.quizujemy.back.models.Article;
import pl.quizujemy.back.models.Curiosities;
import pl.quizujemy.back.repositories.CuriositiesRepository;

import javax.validation.Valid;

@RestController
public class CuriositiesController {

    @Autowired
    private CuriositiesRepository curiositiesRepository;

    @PostMapping("/curiosities/add")
    @CrossOrigin(origins = "*")
    public Curiosities createCuriosity(@Valid @RequestBody Curiosities curiosities){
        return curiositiesRepository.save(curiosities);
    }

    @GetMapping(path = "/curiosities")
    public @ResponseBody Iterable<Curiosities> getAllCuriosities(){
        return curiositiesRepository.findAll();
    }

    @GetMapping(path = "/curiosities/paginated")
    public @ResponseBody Page<Curiosities> getAllCuriosities(Pageable pageable){
        return curiositiesRepository.findAll(pageable);
    }
}
