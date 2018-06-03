package pl.quizujemy.back.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstatistics")
    private Long id;

    private Integer answeredQuestions;
    private Integer correctAnswers;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_iduser", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
