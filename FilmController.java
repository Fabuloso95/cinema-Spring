package org.elis.controller;

import java.util.*;
import org.elis.dto.*;
import org.elis.facade.FilmFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/films")
public class FilmController 
{
    private final FilmFacade filmFacade;

    public FilmController(FilmFacade filmFacade) 
    {
        this.filmFacade = filmFacade;
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() 
    {
        List<FilmDTO> filmDTOs = filmFacade.getAllFilms();
        return new ResponseEntity<>(filmDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable Long id) 
    {
        FilmDTO filmDTO = filmFacade.getFilmById(id);
        return new ResponseEntity<>(filmDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@RequestBody FilmDTO filmDTO) 
    {
        FilmDTO createdFilmDTO = filmFacade.createFilm(filmDTO);
        return new ResponseEntity<>(createdFilmDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Long id, @RequestBody FilmDTO filmDTO) 
    {
        FilmDTO updatedFilmDTO = filmFacade.updateFilm(id, filmDTO);
        return new ResponseEntity<>(updatedFilmDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) 
    {
        filmFacade.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{filmId}/attori/{attoreId}")
    public ResponseEntity<Void> addAttoreToFilm(@PathVariable Long filmId, @PathVariable Long attoreId) 
    {
        filmFacade.addAttoreToFilm(filmId, attoreId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{filmId}/attori/{attoreId}")
    public ResponseEntity<Void> removeAttoreFromFilm(@PathVariable Long filmId, @PathVariable Long attoreId) 
    {
        filmFacade.removeAttoreFromFilm(filmId, attoreId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{filmId}/regista/{registaId}")
    public ResponseEntity<Void> setRegistaToFilm(@PathVariable Long filmId, @PathVariable Long registaId) 
    {
        filmFacade.setRegistaToFilm(filmId, registaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/with-spettacoli")
    public ResponseEntity<List<FilmConSpettacoliDTO>> getFilmConSpettacoli() 
    {
        List<FilmConSpettacoliDTO> filmsWithSpettacoli = filmFacade.getFilmConSpettacoli();
        return ResponseEntity.ok(filmsWithSpettacoli);
    }

    @GetMapping("/{id}/with-spettacoli")
    public ResponseEntity<FilmConSpettacoliDTO> getFilmConSpettacoliById(@PathVariable Long id) 
    {
        Optional<FilmConSpettacoliDTO> filmWithSpettacoli = filmFacade.getFilmConSpettacoliById(id);
        return filmWithSpettacoli.map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/by-titolo")
    public ResponseEntity<FilmDTO> findByTitoloIgnoreCase(@RequestParam String titolo) 
    {
        Optional<FilmDTO> film = filmFacade.findByTitoloIgnoreCase(titolo);
        return film.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/by-genere")
    public ResponseEntity<List<FilmDTO>> findByGenereIgnoreCase(@RequestParam String genere) 
    {
        List<FilmDTO> films = filmFacade.findByGenereIgnoreCase(genere);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/search/by-anno")
    public ResponseEntity<List<FilmDTO>> findByAnnoUscita(@RequestParam int anno) 
    {
        List<FilmDTO> films = filmFacade.findByAnnoUscita(anno);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/search/by-durata")
    public ResponseEntity<List<FilmDTO>> findByDurataBetween(@RequestParam int minDurata, @RequestParam int maxDurata) 
    {
        List<FilmDTO> films = filmFacade.findByDurataBetween(minDurata, maxDurata);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/search/by-regista/{registaId}")
    public ResponseEntity<List<FilmDTO>> findByRegistaId(@PathVariable Long registaId) 
    {
        List<FilmDTO> films = filmFacade.findByRegistaId(registaId);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/search/by-attore/{attoreId}")
    public ResponseEntity<List<FilmDTO>> findByAttoriId(@PathVariable Long attoreId) 
    {
        List<FilmDTO> films = filmFacade.findByAttoriId(attoreId);
        return ResponseEntity.ok(films);
    }
}