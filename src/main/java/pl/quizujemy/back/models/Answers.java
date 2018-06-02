package pl.quizujemy.back.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idanswers")
    private Long id;

    @Size(max = 100)
    private String first;
    @Size(max = 100)
    private String second;
    @Size(max = 100)
    private String third;
    @Size(max = 100)
    private String fourth;
    @Size(max = 100)
    private String correct;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_idquestion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Question question;

}
