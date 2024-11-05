package Nicolo_Mecca.W6_d2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String destinazione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String stato;

    public Viaggio(String destinazione, LocalDate dataInizio, LocalDate dataFine, String stato) {
        this.destinazione = destinazione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.stato = stato;
    }
}