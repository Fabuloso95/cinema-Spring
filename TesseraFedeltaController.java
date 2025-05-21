package org.elis.controller;

import java.util.List;
import org.elis.dto.TesseraFedeltaDTO;
import org.elis.facade.TesseraFedeltaFacade;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tessere-fedelta")
public class TesseraFedeltaController 
{
    private final TesseraFedeltaFacade tesseraFedeltaFacade;

    public TesseraFedeltaController(TesseraFedeltaFacade tesseraFedeltaFacade) 
    {
        this.tesseraFedeltaFacade = tesseraFedeltaFacade;
    }

    @GetMapping
    public ResponseEntity<List<TesseraFedeltaDTO>> getAllTessereFedelta() 
    {
        List<TesseraFedeltaDTO> tessereFedeltaDTOs = tesseraFedeltaFacade.getAllTessereFedelta();
        return ResponseEntity.ok(tessereFedeltaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TesseraFedeltaDTO> getTesseraFedeltaById(@PathVariable Long id) 
    {
        TesseraFedeltaDTO tesseraFedeltaDTO = tesseraFedeltaFacade.getTesseraFedeltaById(id);
        return ResponseEntity.ok(tesseraFedeltaDTO);
    }

    @PostMapping
    public ResponseEntity<TesseraFedeltaDTO> createTesseraFedelta(@RequestBody TesseraFedeltaDTO tesseraFedeltaDTO) 
    {
        TesseraFedeltaDTO createdTesseraFedeltaDTO = tesseraFedeltaFacade.createTesseraFedelta(tesseraFedeltaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTesseraFedeltaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TesseraFedeltaDTO> updateTesseraFedelta(@PathVariable Long id, @RequestBody TesseraFedeltaDTO tesseraFedeltaDTO) 
    {
        TesseraFedeltaDTO updatedTesseraFedeltaDTO = tesseraFedeltaFacade.updateTesseraFedelta(id, tesseraFedeltaDTO);
        return ResponseEntity.ok(updatedTesseraFedeltaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTesseraFedelta(@PathVariable Long id) 
    {
        tesseraFedeltaFacade.deleteTesseraFedelta(id);
        return ResponseEntity.noContent().build();
    }
}