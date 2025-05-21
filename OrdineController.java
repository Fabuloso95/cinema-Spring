package org.elis.controller;

import java.util.List;
import org.elis.dto.OrdineDTO;
import org.elis.facade.OrdineFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema/api/ordini")
public class OrdineController
{
    private final OrdineFacade ordineFacade; 

    public OrdineController(OrdineFacade ordineFacade) 
    {
        this.ordineFacade = ordineFacade;
    }

    @GetMapping
    public ResponseEntity<List<OrdineDTO>> getAllOrdini() 
    {
        List<OrdineDTO> ordiniDTO = ordineFacade.getAllOrdini();
        return ResponseEntity.ok(ordiniDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdineDTO> getOrdineById(@PathVariable Long id) 
    {
        OrdineDTO ordineDTO = ordineFacade.getOrdineById(id);
        return ResponseEntity.ok(ordineDTO);
    }

    @PostMapping
    public ResponseEntity<OrdineDTO> createOrdine(@RequestBody OrdineDTO ordineDTO) 
    {
        OrdineDTO createdOrdineDTO = ordineFacade.createOrdine(ordineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrdineDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdineDTO> updateOrdine(@PathVariable Long id, @RequestBody OrdineDTO ordineDTO) 
    {
        OrdineDTO updatedOrdineDTO = ordineFacade.updateOrdine(id, ordineDTO);
        return ResponseEntity.ok(updatedOrdineDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdine(@PathVariable Long id) 
    {
        ordineFacade.deleteOrdine(id);
        return ResponseEntity.noContent().build();
    }
}