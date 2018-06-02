package pl.quizujemy.back.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idarticle")
    private Long id;
    @Size(max = 100)
    private String title;
    @Size(max = 45)
    private String autor;
    @Size(max = 200)
    private String image;
}
