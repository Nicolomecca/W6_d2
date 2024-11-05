package Nicolo_Mecca.W6_d2.controllers;

import Nicolo_Mecca.W6_d2.entities.Prenotazione;
import Nicolo_Mecca.W6_d2.entities.Viaggio;
import Nicolo_Mecca.W6_d2.excepetions.BadRequestException;
import Nicolo_Mecca.W6_d2.payloads.AssegnazioneViaggioDTO;
import Nicolo_Mecca.W6_d2.payloads.NewPrenotazioneDTO;
import Nicolo_Mecca.W6_d2.payloads.NewViaggioDTO;
import Nicolo_Mecca.W6_d2.payloads.ViaggioWithDipendenteDTO;
import Nicolo_Mecca.W6_d2.services.PrenotazioneService;
import Nicolo_Mecca.W6_d2.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;
    @Autowired
    private PrenotazioneService prenotazioneService;

    // 1. GET http://localhost:3001/viaggi
    @GetMapping
    public Page<Viaggio> getViaggi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.viaggioService.findAll(page, size, sortBy);
    }

    // 2. POST http://localhost:3001/viaggi
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated NewViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.viaggioService.save(body);
    }

    // 3. GET http://localhost:3001/viaggi/{viaggioId}
    @GetMapping("/{viaggioId}")
    public Viaggio findById(@PathVariable Long viaggioId) {
        return this.viaggioService.findById(viaggioId);
    }

    @PutMapping("/{viaggioId}/stato")
    public Viaggio updateStato(
            @PathVariable Long viaggioId,
            @RequestBody Map<String, String> requestBody) {
        String nuovoStato = requestBody.get("stato");
        return this.viaggioService.updateStato(viaggioId, nuovoStato);
    }

    @PostMapping("/assegna")
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioWithDipendenteDTO assegnaDipententeAViaggio(
            @RequestBody @Validated AssegnazioneViaggioDTO body) {

        NewPrenotazioneDTO prenotazioneDTO = new NewPrenotazioneDTO(
                body.dataViaggio(),
                body.note(),
                body.viaggioId(),
                body.dipendenteId()
        );

        Prenotazione prenotazione = prenotazioneService.save(prenotazioneDTO);
        return ViaggioWithDipendenteDTO.fromViaggio(prenotazione.getViaggio(), body.dipendenteId());
    }

    // 5. DELETE http://localhost:3001/viaggi/{viaggioId}
    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long viaggioId) {
        this.viaggioService.findByIdAndDelete(viaggioId);
    }

    // 6. PUT http://localhost:3001/viaggi/{viaggioId}
    @PutMapping("/{viaggioId}")
    public Viaggio findByIdAndUpdate(
            @PathVariable Long viaggioId,
            @RequestBody @Validated NewViaggioDTO body,
            BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.viaggioService.findByIdAndUpdate(viaggioId, body);
    }
}
