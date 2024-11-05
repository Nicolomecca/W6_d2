package Nicolo_Mecca.W6_d2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record DipendentiLoginDTO(

        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password
) {
}
