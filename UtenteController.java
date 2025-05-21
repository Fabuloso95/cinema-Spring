package org.elis.controller;

import org.elis.dto.*;
import org.elis.facade.UtenteFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController 
{
    private final UtenteFacade utenteFacade;

    public UtenteController(UtenteFacade utenteFacade) 
    {
        this.utenteFacade = utenteFacade;
    }

    @GetMapping
    public ResponseEntity<List<UtenteDTO>> getAllUtenti() 
    {
        List<UtenteDTO> utentiDTO = utenteFacade.getAllUtenti();
        return ResponseEntity.ok(utentiDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getUtenteById(@PathVariable Long id) 
    {
        UtenteDTO utenteDTO = utenteFacade.getUtenteById(id);
        return ResponseEntity.ok(utenteDTO);
    }

    @GetMapping("/{id}/biglietti")
    public ResponseEntity<List<BigliettoDTO>> getBigliettiAcquistati(@PathVariable Long id) 
    {
        List<BigliettoDTO> biglietti = utenteFacade.getBigliettiAcquistati(id);
        return ResponseEntity.ok(biglietti);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UtenteDTO> getByUsername(@PathVariable String username) 
    {
        return utenteFacade.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/nomecognome")
    public ResponseEntity<List<UtenteDTO>> searchByNomeECognome(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cognome)
    {
        List<UtenteDTO> utenti = utenteFacade.searchByNomeECognome(nome, cognome);
        return ResponseEntity.ok(utenti);
    }

    @GetMapping("/search/dob/before/{data}")
    public ResponseEntity<List<UtenteDTO>> getByDataNascitaBefore(@PathVariable LocalDate data) 
    {
        List<UtenteDTO> utenti = utenteFacade.findByDataNascitaBefore(data);
        return ResponseEntity.ok(utenti);
    }

    @GetMapping("/search/dob/after/{data}")
    public ResponseEntity<List<UtenteDTO>> getByDataNascitaAfter(@PathVariable LocalDate data) 
    {
        List<UtenteDTO> utenti = utenteFacade.findByDataNascitaAfter(data);
        return ResponseEntity.ok(utenti);
    }

    @GetMapping("/search/dob/between")
    public ResponseEntity<List<UtenteDTO>> getByDataNascitaBetween(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) 
    {
        List<UtenteDTO> utenti = utenteFacade.findByDataNascitaBetween(from, to);
        return ResponseEntity.ok(utenti);
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<UtenteDTO>> searchAllByTermine(@RequestParam String q) 
    {
        List<UtenteDTO> utenti = utenteFacade.searchUtenti(q);
        return ResponseEntity.ok(utenti);
    }

    @PostMapping
    public ResponseEntity<UtenteDTO> createUtente(@RequestBody UtenteDTO utenteDTO) 
    {
        UtenteDTO createdUtenteDTO = utenteFacade.createUtente(utenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUtenteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtenteDTO> updateUtente(@PathVariable Long id, @RequestBody UtenteDTO utenteDTO) 
    {
        UtenteDTO updatedUtenteDTO = utenteFacade.updateUtente(id, utenteDTO);
        return ResponseEntity.ok(updatedUtenteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable Long id) 
    {
        utenteFacade.deleteUtente(id);
        return ResponseEntity.noContent().build();
    }
}