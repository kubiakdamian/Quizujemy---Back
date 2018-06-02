package pl.quizujemy.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.quizujemy.back.models.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
    Iterable<Answers> findByQuestionId(Long idquestion);
}
