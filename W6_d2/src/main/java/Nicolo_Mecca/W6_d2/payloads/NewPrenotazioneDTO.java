package Nicolo_Mecca.W6_d2.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewPrenotazioneDTO(
        @NotNull(message = "La data del viaggio è obbligatoria")
        @Future(message = "La data del viaggio deve essere futura")
        LocalDate dataViaggio,

        @NotNull(message = "Le note sono obbligatorie")
        @Size(min = 3, max = 500, message = "Le note devono essere comprese tra 3 e 500 caratteri")
        String note,

        @NotNull(message = "L'ID del viaggio è obbligatorio")
        Long viaggioId,

        @NotNull(message = "L'ID del dipendente è obbligatorio")
        Long dipendenteId
) {
}