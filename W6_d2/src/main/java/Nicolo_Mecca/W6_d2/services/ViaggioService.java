package Nicolo_Mecca.W6_d2.services;

import Nicolo_Mecca.W6_d2.entities.Prenotazione;
import Nicolo_Mecca.W6_d2.entities.Viaggio;
import Nicolo_Mecca.W6_d2.excepetions.BadRequestException;
import Nicolo_Mecca.W6_d2.excepetions.NotFoundException;
import Nicolo_Mecca.W6_d2.payloads.NewPrenotazioneDTO;
import Nicolo_Mecca.W6_d2.payloads.NewViaggioDTO;
import Nicolo_Mecca.W6_d2.payloads.ViaggioWithDipendenteDTO;
import Nicolo_Mecca.W6_d2.repositories.DipendenteRepository;
import Nicolo_Mecca.W6_d2.repositories.PrenotazioneRepository;
import Nicolo_Mecca.W6_d2.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private PrenotazioneService prenotazioneService;

    public Viaggio findById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio con id " + id + " non trovato"));
    }

    public Viaggio save(NewViaggioDTO body) {
        // Validazione delle date
        if (body.dataFine().isBefore(body.dataInizio())) {
            throw new BadRequestException("La data di fine non può essere precedente alla data di inizio");
        }

        Viaggio newViaggio = new Viaggio();
        newViaggio.setDestinazione(body.destinazione());
        newViaggio.setDataInizio(body.dataInizio());
        newViaggio.setDataFine(body.dataFine());
        newViaggio.setStato(body.stato());

        return viaggioRepository.save(newViaggio);
    }

    public Page<Viaggio> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioRepository.findAll(pageable);
    }

    public void findByIdAndDelete(Long id) {
        Viaggio found = this.findById(id);
        viaggioRepository.delete(found);
    }

    public Viaggio findByIdAndUpdate(Long id, NewViaggioDTO body) {
        Viaggio found = this.findById(id);
        if (!body.dataFine().equals(found.getDataFine()) && !body.dataInizio().equals(found.getDataInizio()) && body.dataFine().isBefore(body.dataInizio())) {
            throw new BadRequestException("La data di fine non può essere precedente alla data di inizio");
        }
        if (!body.destinazione().equals(found.getDestinazione())) {
            found.setDestinazione(body.destinazione());
        }

        if (!body.dataInizio().equals(found.getDataInizio())) {
            found.setDataInizio(body.dataInizio());
        }

        if (!body.dataFine().equals(found.getDataFine())) {
            found.setDataFine(body.dataFine());
        }

        return viaggioRepository.save(found);
    }

    public Viaggio updateStato(Long id, String nuovoStato) {
        if (!nuovoStato.equals("in programma") && !nuovoStato.equals("completato")) {
            throw new BadRequestException("Stato non valido. Stati ammessi: in programma, completato");
        }

        Viaggio found = this.findById(id);
        found.setStato(nuovoStato);
        return viaggioRepository.save(found);
    }

    public ViaggioWithDipendenteDTO assegnaDipendenteAViaggio(Long viaggioId, Long dipendenteId, NewPrenotazioneDTO body) {
        Prenotazione prenotazione = prenotazioneService.save(body);
        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));
        return ViaggioWithDipendenteDTO.fromViaggio(viaggio, dipendenteId);
    }


}


