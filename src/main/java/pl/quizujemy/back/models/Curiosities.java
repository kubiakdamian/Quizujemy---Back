package pl.quizujemy.back.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Curiosities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcuriosities;
    private String content;

}

