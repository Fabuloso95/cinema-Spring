package org.elis.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.elis.dto.*;
import org.elis.facade.SpettacoloFacade;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spettacoli")
public class SpettacoloController 
{
    private final SpettacoloFacade spettacoloFacade;

    public SpettacoloController(SpettacoloFacade spettacoloFacade) 
    {
        this.spettacoloFacade = spettacoloFacade;
    }

    @GetMapping
    public ResponseEntity<List<SpettacoloDTO>> getAllSpettacoli() 
    {
        List<SpettacoloDTO> spettacoliDTO = spettacoloFacade.getAllSpettacoli();
        return new ResponseEntity<>(spettacoliDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpettacoloDTO> getSpettacoloById(@PathVariable Long id) 
    {
        SpettacoloDTO spettacoloDTO = spettacoloFacade.getSpettacoloById(id);
        return new ResponseEntity<>(spettacoloDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SpettacoloDTO> createSpettacolo(@RequestBody SpettacoloCreationDTO spettacoloDTO) 
    {
        SpettacoloDTO createdSpettacoloDTO = spettacoloFacade.createSpettacolo(spettacoloDTO);
        return new ResponseEntity<>(createdSpettacoloDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpettacoloDTO> updateSpettacolo(@PathVariable Long id, @RequestBody SpettacoloUpdateDTO spettacolo) 
    {
        SpettacoloDTO updatedSpettacolo = spettacoloFacade.updateSpettacolo(id, spettacolo);
        return new ResponseEntity<>(updatedSpettacolo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpettacolo(@PathVariable Long id)
    {
        spettacoloFacade.deleteSpettacolo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/dettaglio")
    public ResponseEntity<DettaglioSpettacoloDTO> getDettaglioSpettacolo(@PathVariable Long id) 
    {
        DettaglioSpettacoloDTO dettaglioDTO = spettacoloFacade.getDettaglioSpettacolo(id);
        return ResponseEntity.ok(dettaglioDTO);
    }

    @GetMapping("/search/by-film/{filmId}")
    public ResponseEntity<List<SpettacoloDTO>> findByFilmId(@PathVariable Long filmId) 
    {
        List<SpettacoloDTO> spettacoli = spettacoloFacade.findByFilmId(filmId);
        return ResponseEntity.ok(spettacoli);
    }

    @GetMapping("/search/by-sala/{salaId}")
    public ResponseEntity<List<SpettacoloDTO>> findBySalaId(@PathVariable Long salaId) 
    {
        List<SpettacoloDTO> spettacoli = spettacoloFacade.findBySalaId(salaId);
        return ResponseEntity.ok(spettacoli);
    }

    @GetMapping("/search/by-data")
    public ResponseEntity<List<SpettacoloDTO>> findByDataOraBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) 
    {
        List<SpettacoloDTO> spettacoli = spettacoloFacade.findByDataOraBetween(start, end);
        return ResponseEntity.ok(spettacoli);
    }
}