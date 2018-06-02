package pl.quizujemy.back.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idquiz")
    private Long id;

    @Size(max = 40)
    private String category;
}
