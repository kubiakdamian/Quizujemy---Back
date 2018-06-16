package pl.quizujemy.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.quizujemy.back.models.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Iterable<Photo> findByUserId(Long iduser);
}
