package org.elis.service;

import org.elis.dto.FilmConSpettacoliDTO;
import org.elis.dto.FilmDTO;
import java.util.List;
import java.util.Optional;

public interface FilmService 
{
    List<FilmDTO> getAllFilms();
    FilmDTO getFilmById(Long id);
    FilmDTO createFilm(FilmDTO filmDTO);
    FilmDTO updateFilm(Long id, FilmDTO filmDTO);
    void deleteFilm(Long id);
    void addAttoreToFilm(Long filmId, Long attoreId);
    void removeAttoreFromFilm(Long filmId, Long attoreId);
    void setRegistaToFilm(Long filmId, Long registaId);
    List<FilmConSpettacoliDTO> getFilmConSpettacoli();
    Optional<FilmConSpettacoliDTO> getFilmConSpettacoliById(Long id);
    Optional<FilmDTO> findByTitoloIgnoreCase(String titolo);
    List<FilmDTO> findByGenereIgnoreCase(String genere);
    List<FilmDTO> findByAnnoUscita(int anno);
    List<FilmDTO> findByDurataBetween(int minDurata, int maxDurata);
    List<FilmDTO> findByRegistaId(Long registaId);
    List<FilmDTO> findByAttoriId(Long attoreId);
}