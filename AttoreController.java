package org.elis.controller;

import org.elis.dto.AttoreDTO;
import org.elis.facade.AttoreFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attori")
public class AttoreController {
    private final AttoreFacade attoreFacade;

    public AttoreController(AttoreFacade attoreFacade) 
    {
        this.attoreFacade = attoreFacade;
    }

    @GetMapping
    public ResponseEntity<List<AttoreDTO>> getAllAttori() 
    {
        List<AttoreDTO> attoreDTOs = attoreFacade.getAllAttori();
        return ResponseEntity.ok(attoreDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttoreDTO> getAttoreById(@PathVariable Long id) 
    {
        AttoreDTO attoreDTO = attoreFacade.getAttoreById(id);
        return ResponseEntity.ok(attoreDTO);
    }

    @PostMapping
    public ResponseEntity<AttoreDTO> createAttore(@RequestBody AttoreDTO attoreDTO) 
    {
        AttoreDTO nuovoAttoreDTO = attoreFacade.createAttore(attoreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoAttoreDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttoreDTO> updateAttore(@PathVariable Long id, @RequestBody AttoreDTO attoreDTO) 
    {
        AttoreDTO attoreAggiornatoDTO = attoreFacade.updateAttore(id, attoreDTO);
        return ResponseEntity.ok(attoreAggiornatoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttore(@PathVariable Long id)
    {
        attoreFacade.deleteAttore(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-nome/{nome}")
    public ResponseEntity<List<AttoreDTO>> getAttoriByNome(@PathVariable String nome) 
    {
        List<AttoreDTO> attori = attoreFacade.getAttoriByNome(nome);
        return ResponseEntity.ok(attori);
    }

    @GetMapping("/by-cognome/{cognome}")
    public ResponseEntity<List<AttoreDTO>> getAttoriByCognome(@PathVariable String cognome)
    {
        List<AttoreDTO> attori = attoreFacade.getAttoriByCognome(cognome);
        return ResponseEntity.ok(attori);
    }
}