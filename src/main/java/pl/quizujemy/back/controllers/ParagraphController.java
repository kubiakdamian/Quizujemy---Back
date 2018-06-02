package pl.quizujemy.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.quizujemy.back.exceptions.ResourceNotFoundException;
import pl.quizujemy.back.models.Paragraph;
import pl.quizujemy.back.repositories.ArticleRepository;
import pl.quizujemy.back.repositories.ParagraphRepository;

import javax.validation.Valid;

@RestController
public class ParagraphController {
    @Autowired
    private ParagraphRepository paragraphRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("articles/{idarticle}/paragraphs")
    public Page<Paragraph> getAllParagraphsByArticleId(@PathVariable(value = "idarticle") Long idarticle, Pageable pageable){
        return paragraphRepository.findByArticleId(idarticle, pageable);
    }

    @PostMapping("articles/{idarticle}/paragraphs")
    public Paragraph createParagraph(@PathVariable (value = "idarticle") Long idarticle, @Valid @RequestBody Paragraph paragraph){
        return articleRepository.findById(idarticle).map(article -> {
            paragraph.setArticle(article);
            return paragraphRepository.save(paragraph);
        }).orElseThrow(() -> new ResourceNotFoundException("idarticle " + idarticle + " not found"));
    }

    @PutMapping("articles/{idarticle}/paragraphs/{idparagraph}")
    public Paragraph updateParagraph(@PathVariable (value = "idarticle") Long idarticle,
                                     @PathVariable (value = "idparagraph") Long idparagraph,
                                     @Valid @RequestBody Paragraph paragraphRequest){
        if(!articleRepository.existsById(idarticle)){
            throw new ResourceNotFoundException("idarticle " + idarticle + " not found");
        }

        return paragraphRepository.findById(idparagraph).map(paragraph -> {
            paragraph.setContent(paragraphRequest.getContent());
            return paragraphRepository.save(paragraph);
        }).orElseThrow(() -> new ResourceNotFoundException("idparagraph " + idparagraph + " not found"));
    }

    @DeleteMapping("articles/{idarticles}/paragraphs/{idparagraph}")
    public ResponseEntity<?> deleteParagraph(@PathVariable(value = "idarticle") Long idarticle,
                                             @PathVariable(value = "idparagraph") Long idparagraph){
        if(!articleRepository.existsById(idarticle)){
            throw new ResourceNotFoundException("idarticle " + idarticle + " not found");
        }

        return paragraphRepository.findById(idparagraph).map(paragraph -> {
            paragraphRepository.delete(paragraph);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("idparagraph " + idparagraph + " not found"));
    }

}
