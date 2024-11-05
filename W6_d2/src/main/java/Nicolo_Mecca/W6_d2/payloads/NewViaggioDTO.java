package Nicolo_Mecca.W6_d2.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewViaggioDTO(
        @NotEmpty(message = "La destinazione è obbligatoria!")
        @Size(min = 2, max = 100, message = "La destinazione deve essere compresa tra 2 e 100 caratteri!")
        String destinazione,

        @NotNull(message = "La data di inizio è obbligatoria!")
        @Future(message = "La data di inizio deve essere futura")
        LocalDate dataInizio,

        @NotNull(message = "La data di fine è obbligatoria!")
        @Future(message = "La data di fine deve essere futura")
        LocalDate dataFine,

        @NotEmpty(message = "Lo stato del viaggio è obbligatorio!")
        @Pattern(regexp = "^(in programma|completato)$", message = "Lo stato può essere solo 'in programma' o 'completato'")
        String stato
) {
}