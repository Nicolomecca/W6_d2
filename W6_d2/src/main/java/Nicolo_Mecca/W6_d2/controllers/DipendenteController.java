package Nicolo_Mecca.W6_d2.controllers;

import Nicolo_Mecca.W6_d2.entities.Dipendente;
import Nicolo_Mecca.W6_d2.excepetions.BadRequestException;
import Nicolo_Mecca.W6_d2.payloads.NewDipendenteDTO;
import Nicolo_Mecca.W6_d2.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    // 1. GET http://localhost:3001/dipendenti
    @GetMapping
    public Page<Dipendente> getDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.dipendenteService.findAll(page, size, sortBy);
    }

    // 2. POST http://localhost:3001/dipendenti
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.dipendenteService.save(body);
    }

    // 3. GET http://localhost:3001/dipendenti/{dipendenteId}
    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable Long dipendenteId) {
        return this.dipendenteService.findById(dipendenteId);
    }

    // 4. PUT http://localhost:3001/dipendenti/{dipendenteId}
    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(
            @PathVariable Long dipendenteId,
            @RequestBody @Validated NewDipendenteDTO body,
            BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }


    // 5. DELETE http://localhost:3001/dipendenti/{dipendenteId}
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long dipendenteId) {
        this.dipendenteService.findByIdAndDelete(dipendenteId);
    }


}
