package pl.quizujemy.back.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.quizujemy.back.models.Paragraph;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {
    Page<Paragraph> findByArticleId(Long idarticle, Pageable pageable);
}
