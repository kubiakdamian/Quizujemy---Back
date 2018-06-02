package pl.quizujemy.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.quizujemy.back.models.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
