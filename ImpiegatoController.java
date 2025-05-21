package org.elis.controller;

import org.elis.dto.ImpiegatoDTO;
import org.elis.dto.FilmDTO;
import org.elis.dto.SpettacoloDTO;
import org.elis.dto.ProdottoMenuDTO;
import org.elis.dto.SpettacoloCreationDTO;
import org.elis.dto.SpettacoloUpdateDTO;
import org.elis.facade.ImpiegatoFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/impiegati")
public class ImpiegatoController 
{
    private final ImpiegatoFacade impiegatoFacade;

    public ImpiegatoController(ImpiegatoFacade impiegatoFacade)
    {
        this.impiegatoFacade = impiegatoFacade;
    }

    @GetMapping
    public ResponseEntity<List<ImpiegatoDTO>> getAllImpiegati() 
    {
        List<ImpiegatoDTO> impiegatiDTOs = impiegatoFacade.getAllImpiegati();
        return ResponseEntity.ok(impiegatiDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpiegatoDTO> getImpiegatoById(@PathVariable Long id) 
    {
        ImpiegatoDTO impiegatoDTO = impiegatoFacade.getImpiegatoById(id);
        return ResponseEntity.ok(impiegatoDTO);
    }

    @PostMapping
    public ResponseEntity<ImpiegatoDTO> createImpiegato(@RequestBody ImpiegatoDTO impiegatoDTO) 
    {
        ImpiegatoDTO nuovoImpiegatoDTO = impiegatoFacade.createImpiegato(impiegatoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoImpiegatoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImpiegatoDTO> updateImpiegato(@PathVariable Long id, @RequestBody ImpiegatoDTO impiegatoDTO) 
    {
        ImpiegatoDTO impiegatoAggiornatoDTO = impiegatoFacade.updateImpiegato(id, impiegatoDTO);
        return ResponseEntity.ok(impiegatoAggiornatoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImpiegato(@PathVariable Long id) 
    {
        impiegatoFacade.deleteImpiegato(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/film")
    public ResponseEntity<FilmDTO> createFilm(@RequestBody FilmDTO filmDTO)
    {
        FilmDTO createdFilm = impiegatoFacade.createFilm(filmDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFilm);
    }

    @PutMapping("/film/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Long id, @RequestBody FilmDTO filmDTO) 
    {
        FilmDTO updatedFilm = impiegatoFacade.updateFilm(id, filmDTO);
        return ResponseEntity.ok(updatedFilm);
    }

    @DeleteMapping("/film/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) 
    {
        impiegatoFacade.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/spettacoli")
    public ResponseEntity<SpettacoloDTO> createSpettacolo(@RequestBody SpettacoloCreationDTO spettacoloDTO) 
    {
        SpettacoloDTO createdSpettacolo = impiegatoFacade.createSpettacolo(spettacoloDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpettacolo);
    }

    @PutMapping("/spettacoli/{id}")
    public ResponseEntity<SpettacoloDTO> updateSpettacolo(@PathVariable Long id, @RequestBody SpettacoloUpdateDTO spettacoloDTO) 
    {
        SpettacoloDTO updatedSpettacolo = impiegatoFacade.updateSpettacolo(id, spettacoloDTO);
        return ResponseEntity.ok(updatedSpettacolo);
    }

    @DeleteMapping("/spettacoli/{id}")
    public ResponseEntity<Void> deleteSpettacolo(@PathVariable Long id) 
    {
        impiegatoFacade.deleteSpettacolo(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/menu")
    public ResponseEntity<ProdottoMenuDTO> createProdottoMenu(@RequestBody ProdottoMenuDTO prodottoMenuDTO) 
    {
        ProdottoMenuDTO createdMenu = impiegatoFacade.createProdottoMenu(prodottoMenuDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<ProdottoMenuDTO> updateProdottoMenu(@PathVariable Long id, @RequestBody ProdottoMenuDTO prodottoMenuDTO) 
    {
        ProdottoMenuDTO updatedMenu = impiegatoFacade.updateProdottoMenu(id, prodottoMenuDTO);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Void> deleteProdottoMenu(@PathVariable Long id) 
    {
        impiegatoFacade.deleteProdottoMenu(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/spettacoli/{id}/prezzo-base")
    public ResponseEntity<SpettacoloDTO> updateSpettacoloPrezzoBase(@PathVariable Long id, @RequestParam double nuovoPrezzoBase) 
    {
        SpettacoloDTO updatedSpettacolo = impiegatoFacade.updateSpettacoloPrezzoBase(id, nuovoPrezzoBase);
        return ResponseEntity.ok(updatedSpettacolo);
    }
}