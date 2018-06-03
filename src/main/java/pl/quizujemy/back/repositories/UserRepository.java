package pl.quizujemy.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.quizujemy.back.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
