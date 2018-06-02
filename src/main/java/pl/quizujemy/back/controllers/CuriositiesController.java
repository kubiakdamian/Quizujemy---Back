package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.quizujemy.back.models.Article;
import pl.quizujemy.back.models.Curiosities;
import pl.quizujemy.back.repositories.CuriositiesRepository;

@Controller
public class CuriositiesController {

    @Autowired
    private CuriositiesRepository curiositiesRepository;

    @GetMapping(path = "/curiosity/add")
    public @ResponseBody String addNewCuriosity (@RequestParam String content){
        Curiosities curiosities = new Curiosities();

        curiosities.setContent(content);
        curiositiesRepository.save(curiosities);

        return "Curiosities added";
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
