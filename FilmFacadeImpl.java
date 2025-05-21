package org.elis.facade.impl;

import org.elis.dto.FilmConSpettacoliDTO;
import org.elis.dto.FilmDTO;
import org.elis.facade.FilmFacade;
import org.elis.service.FilmService;
import org.springframework.stereotype.*;
import java.util.List;
import java.util.Optional;

@Service
public class FilmFacadeImpl implements FilmFacade 
{
    private final FilmService filmService;

    public FilmFacadeImpl(FilmService filmService) 
    {
        this.filmService = filmService;
    }

    @Override
    public List<FilmDTO> getAllFilms() 
    {
        return filmService.getAllFilms();
    }

    @Override
    public FilmDTO getFilmById(Long id) 
    {
        return filmService.getFilmById(id);
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) 
    {
        return filmService.createFilm(filmDTO);
    }

    @Override
    public FilmDTO updateFilm(Long id, FilmDTO filmDTO)
    {
        return filmService.updateFilm(id, filmDTO);
    }

    @Override
    public void deleteFilm(Long id) 
    {
        filmService.deleteFilm(id);
    }

    @Override
    public void addAttoreToFilm(Long filmId, Long attoreId) 
    {
        filmService.addAttoreToFilm(filmId, attoreId);
    }

    @Override
    public void removeAttoreFromFilm(Long filmId, Long attoreId) 
    {
        filmService.removeAttoreFromFilm(filmId, attoreId);
    }

    @Override
    public void setRegistaToFilm(Long filmId, Long registaId) 
    {
        filmService.setRegistaToFilm(filmId, registaId);
    }

    @Override
    public List<FilmConSpettacoliDTO> getFilmConSpettacoli() 
    {
        return filmService.getFilmConSpettacoli();
    }

    @Override
    public Optional<FilmConSpettacoliDTO> getFilmConSpettacoliById(Long id) 
    {
        return filmService.getFilmConSpettacoliById(id);
    }

    @Override
    public Optional<FilmDTO> findByTitoloIgnoreCase(String titolo)
    {
        return filmService.findByTitoloIgnoreCase(titolo);
    }

    @Override
    public List<FilmDTO> findByGenereIgnoreCase(String genere) 
    {
        return filmService.findByGenereIgnoreCase(genere);
    }

    @Override
    public List<FilmDTO> findByAnnoUscita(int anno) 
    {
        return filmService.findByAnnoUscita(anno);
    }

    @Override
    public List<FilmDTO> findByDurataBetween(int minDurata, int maxDurata) 
    {
        return filmService.findByDurataBetween(minDurata, maxDurata);
    }

    @Override
    public List<FilmDTO> findByRegistaId(Long registaId) 
    {
        return filmService.findByRegistaId(registaId);
    }

    @Override
    public List<FilmDTO> findByAttoriId(Long attoreId) 
    {
        return filmService.findByAttoriId(attoreId);
    }
}