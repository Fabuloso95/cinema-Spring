package org.elis.controller;

import java.util.List;
import org.elis.dto.SalaDTO;
import org.elis.facade.SalaFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sale")
public class SalaController 
{
    private final SalaFacade salaFacade;

    public SalaController(SalaFacade salaFacade) 
    {
        this.salaFacade = salaFacade;
    }

    @GetMapping
    public ResponseEntity<List<SalaDTO>> getAllSale() 
    {
        List<SalaDTO> saleDTO = salaFacade.getAllSale();
        return new ResponseEntity<>(saleDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> getSalaById(@PathVariable Long id) 
    {
        SalaDTO salaDTO = salaFacade.getSalaById(id);
        return new ResponseEntity<>(salaDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SalaDTO> createSala(@RequestBody SalaDTO salaDTO) 
    {
        SalaDTO createdSalaDTO = salaFacade.createSala(salaDTO);
        return new ResponseEntity<>(createdSalaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaDTO> updateSala(@PathVariable Long id, @RequestBody SalaDTO salaDTO) 
    {
        SalaDTO updatedSalaDTO = salaFacade.updateSala(id, salaDTO);
        return new ResponseEntity<>(updatedSalaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable Long id) 
    {
        salaFacade.deleteSala(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<SalaDTO> getSalaByNome(@PathVariable String nome) 
    {
        SalaDTO salaDTO = salaFacade.getSalaByNome(nome);
        return new ResponseEntity<>(salaDTO, HttpStatus.OK);
    }

    @GetMapping("/capienza/{min}")
    public ResponseEntity<List<SalaDTO>> getSaleByCapienza(@PathVariable int min)
    {
        List<SalaDTO> saleDTO = salaFacade.getSaleByCapienza(min);
        return new ResponseEntity<>(saleDTO, HttpStatus.OK);
    }

    @GetMapping("/attive")
    public ResponseEntity<List<SalaDTO>> getSaleUsufruibili() 
    {
        List<SalaDTO> saleDTO = salaFacade.getSaleUsufruibili();
        return new ResponseEntity<>(saleDTO, HttpStatus.OK);
    }
}