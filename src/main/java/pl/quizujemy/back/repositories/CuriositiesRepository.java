package pl.quizujemy.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.quizujemy.back.models.Curiosities;

public interface CuriositiesRepository extends JpaRepository<Curiosities, Long> {
}
