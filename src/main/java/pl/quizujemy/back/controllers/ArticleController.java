package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.quizujemy.back.exceptions.ResourceNotFoundException;
import pl.quizujemy.back.models.Article;
import pl.quizujemy.back.repositories.ArticleRepository;

import javax.validation.Valid;

@RestController
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/articles")
    public Page<Article> getAllArticles(Pageable pageable){
        return articleRepository.findAll(pageable);
    }

    @PostMapping("/articles")
    public Article createArticle(@Valid @RequestBody Article article){
        return articleRepository.save(article);
    }

    @PutMapping("/articles/{idarticle}")
    public Article updateArticle(@PathVariable Long idarticle, @Valid @RequestBody Article articleRequest){
        return articleRepository.findById(idarticle).map(article -> {
            article.setAutor(articleRequest.getAutor());
            article.setTitle(articleRequest.getTitle());
            article.setImage(articleRequest.getImage());
            return articleRepository.save(article);
        }).orElseThrow(() -> new ResourceNotFoundException("idarticle " + idarticle + " not found"));
    }

    @DeleteMapping("articles/{idarticle}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long idarticle) {
        return articleRepository.findById(idarticle).map(article -> {
            articleRepository.delete(article);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("idarticle " + idarticle + " not found"));
    }
}