package pl.quizujemy.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.quizujemy.back.models.Anteroom;

public interface AnteroomRepository extends JpaRepository<Anteroom, Long> {
}
