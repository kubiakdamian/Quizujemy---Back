package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.quizujemy.back.models.Curiosities;
import pl.quizujemy.back.repositories.CuriositiesRepository;

@Controller
@RequestMapping(path = "/api")
public class CuriositiesController {

    @Autowired
    private CuriositiesRepository curiositiesRepository;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewCuriosity (@RequestParam String content){
        Curiosities curiosities = new Curiosities();

        curiosities.setContent(content);
        curiositiesRepository.save(curiosities);

        return "Curiosities added";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Curiosities> getAllCuriosities(){
        return curiositiesRepository.findAll();
    }
}
