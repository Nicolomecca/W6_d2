package Nicolo_Mecca.W6_d2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate dataViaggio;

    private LocalDateTime dataPrenotazione;

    private String note;

    @OneToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    public Prenotazione(LocalDate dataViaggio, String note, Viaggio viaggio) {
        this.dataViaggio = dataViaggio;
        this.dataPrenotazione = LocalDateTime.now();
        this.note = note;
        this.viaggio = viaggio;
    }
}