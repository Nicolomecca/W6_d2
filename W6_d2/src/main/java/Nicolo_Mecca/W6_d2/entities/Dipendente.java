package Nicolo_Mecca.W6_d2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String imgProfilo;
    private String password;


    public Dipendente(String password, String imgProfilo, String email, String cognome, String nome, String username) {
        this.password = password;
        this.imgProfilo = imgProfilo;
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
        this.username = username;
    }
}