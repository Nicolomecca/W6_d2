package Nicolo_Mecca.W6_d2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(
        @NotEmpty(message = "Il nome utente è obbligatorio!")
        @Size(min = 3, max = 20, message = "Il nome utente deve essere compreso tra 3 e 20 caratteri!")
        String username,

        @NotEmpty(message = "Il nome è obbligatorio!")
        @Size(min = 2, max = 40, message = "Il nome deve essere compreso tra 2 e 40 caratteri!")
        String nome,

        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 2, max = 40, message = "Il cognome deve essere compreso tra 2 e 40 caratteri!")
        String cognome,

        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "la password è un campo obbligatorio")
        @Size(min = 4, message = "la password deve avere almeno 4 caratteri")
        String password) {

}


