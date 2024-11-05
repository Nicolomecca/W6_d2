package Nicolo_Mecca.W6_d2.repositories;

import Nicolo_Mecca.W6_d2.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);

}
