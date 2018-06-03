package pl.quizujemy.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.quizujemy.back.models.Statistics;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Iterable<Statistics> findByUserId(Long iduser);
}
