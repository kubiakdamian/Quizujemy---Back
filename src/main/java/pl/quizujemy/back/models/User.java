package pl.quizujemy.back.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Long id;

    @Size(max = 25)
    private String username;

    @Size(max = 100)
    private String email;

    @Size(max = 200)
    private String password;

    private Integer role;
}
