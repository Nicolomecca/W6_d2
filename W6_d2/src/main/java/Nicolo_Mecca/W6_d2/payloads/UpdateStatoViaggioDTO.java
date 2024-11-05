package Nicolo_Mecca.W6_d2.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UpdateStatoViaggioDTO(
        @NotEmpty(message = "Lo stato del viaggio è obbligatorio!")
        @Pattern(regexp = "^(in programma|completato)$", message = "Lo stato può essere solo 'in programma' o 'completato'")
        String stato
) {
}