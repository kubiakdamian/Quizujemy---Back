package pl.quizujemy.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.quizujemy.back.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
