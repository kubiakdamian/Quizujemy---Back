package pl.quizujemy.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.quizujemy.back.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Iterable<Question> findByQuizId(Long idquiz);
}
