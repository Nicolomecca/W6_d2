package Nicolo_Mecca.W6_d2.payloads;

import Nicolo_Mecca.W6_d2.entities.Viaggio;

import java.time.LocalDate;

public record ViaggioWithDipendenteDTO(
        Long id,
        String destinazione,
        LocalDate dataInizio,
        LocalDate dataFine,
        String stato,
        Long dipendenteId
) {
    public static ViaggioWithDipendenteDTO fromViaggio(Viaggio viaggio, Long dipendenteId) {
        return new ViaggioWithDipendenteDTO(
                viaggio.getId(),
                viaggio.getDestinazione(),
                viaggio.getDataInizio(),
                viaggio.getDataFine(),
                viaggio.getStato(),
                dipendenteId
        );
    }
}