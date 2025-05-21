package org.elis.controller;

import java.util.List;
import java.util.Optional;
import org.elis.dto.RegistaDTO;
import org.elis.facade.RegistaFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registi")
public class RegistaController 
{
    private final RegistaFacade registaFacade;

    public RegistaController(RegistaFacade registaFacade) 
    {
        this.registaFacade = registaFacade;
    }

    @GetMapping
    public ResponseEntity<List<RegistaDTO>> getAllRegisti() 
    {
        List<RegistaDTO> registiDTO = registaFacade.getAllRegisti();
        return new ResponseEntity<>(registiDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistaDTO> getRegistaById(@PathVariable Long id) 
    {
        RegistaDTO registaDTO = registaFacade.getRegistaById(id);
        return ResponseEntity.ok(registaDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<RegistaDTO> findByNomeAndCognome(@RequestParam String nome, @RequestParam String cognome) 
    {
        Optional<RegistaDTO> registaDTO = registaFacade.findByNomeAndCognome(nome, cognome);
        return registaDTO.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegistaDTO> createRegista(@RequestBody RegistaDTO registaDTO) 
    {
        RegistaDTO createdRegistaDTO = registaFacade.createRegista(registaDTO);
        return new ResponseEntity<>(createdRegistaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistaDTO> updateRegista(@PathVariable Long id, @RequestBody RegistaDTO registaDTO) 
    {
        RegistaDTO updatedRegistaDTO = registaFacade.updateRegista(id, registaDTO);
        return ResponseEntity.ok(updatedRegistaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegista(@PathVariable Long id) 
    {
        registaFacade.deleteRegista(id);
        return ResponseEntity.noContent().build();
    }
}