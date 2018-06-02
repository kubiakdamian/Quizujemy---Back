package pl.quizujemy.back.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.quizujemy.back.models.Curiosities;

public interface CuriositiesRepository extends CrudRepository<Curiosities, Long> {
}
