package pl.quizujemy.back.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 25)
    private String username;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String email;
    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String password;
    @NotNull
    @NotEmpty
    private Integer role;
}
