package Nicolo_Mecca.W6_d2.services;

import Nicolo_Mecca.W6_d2.entities.Dipendente;
import Nicolo_Mecca.W6_d2.excepetions.UnauthorizedException;
import Nicolo_Mecca.W6_d2.payloads.DipendentiLoginDTO;
import Nicolo_Mecca.W6_d2.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWT jwt;

    public String checkCredentialsAndGenerateToken(DipendentiLoginDTO body) {
        Dipendente found = this.dipendenteService.findByEmail(body.email());
        if (found.getPassword().equals(body.password())) {
            String accessToken = jwt.createToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali errate");
        }
    }

}
