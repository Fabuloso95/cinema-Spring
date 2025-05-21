package org.elis.controller;

import java.util.List;
import org.elis.dto.BigliettoDTO;
import org.elis.facade.BigliettoFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biglietti")
public class BigliettoController 
{
    private final BigliettoFacade bigliettoFacade;

    public BigliettoController(BigliettoFacade bigliettoFacade) 
    {
        this.bigliettoFacade = bigliettoFacade;
    }

    @GetMapping
    public ResponseEntity<List<BigliettoDTO>> getAllBiglietti() 
    {
        List<BigliettoDTO> biglietti = bigliettoFacade.getAllBiglietti();
        return ResponseEntity.ok(biglietti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BigliettoDTO> getBigliettoById(@PathVariable Long id)
    {
        BigliettoDTO biglietto = bigliettoFacade.getBigliettoById(id);
        return ResponseEntity.ok(biglietto);
    }

    @PostMapping
    public ResponseEntity<BigliettoDTO> createBiglietto(@RequestBody BigliettoDTO bigliettoDTO) 
    {
        BigliettoDTO created = bigliettoFacade.createBiglietto(bigliettoDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BigliettoDTO> updateBiglietto(@PathVariable Long id, @RequestBody BigliettoDTO bigliettoDTO) 
    {
        BigliettoDTO updated = bigliettoFacade.updateBiglietto(id, bigliettoDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBiglietto(@PathVariable Long id) 
    {
        bigliettoFacade.deleteBiglietto(id);
        return ResponseEntity.noContent().build();
    }
}