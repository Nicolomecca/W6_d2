package Nicolo_Mecca.W6_d2.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dipendente implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    private Role role;


    public Dipendente(String password, String imgProfilo, String email, String cognome, String nome, String username) {
        this.password = password;
        this.imgProfilo = imgProfilo;
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
        this.username = username;
        this.role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}