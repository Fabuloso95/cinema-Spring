package org.elis.service;

import org.elis.dto.*;
import java.util.*;

public interface ImpiegatoService
{
    List<ImpiegatoDTO> getAllImpiegati();
    ImpiegatoDTO getImpiegatoById(Long id);
    ImpiegatoDTO createImpiegato(ImpiegatoDTO impiegatoDTO);
    ImpiegatoDTO updateImpiegato(Long id, ImpiegatoDTO impiegatoDTO);
    void deleteImpiegato(Long id);
    Optional<ImpiegatoDTO> findByUsername(String username);
    FilmDTO createFilm(FilmDTO filmDTO);
    FilmDTO updateFilm(Long id, FilmDTO filmDTO);
    void deleteFilm(Long id);
    SpettacoloDTO createSpettacolo(SpettacoloCreationDTO spettacoloDTO);
    SpettacoloDTO updateSpettacolo(Long id, SpettacoloUpdateDTO spettacoloDTO);
    void deleteSpettacolo(Long id);
    ProdottoMenuDTO createProdottoMenu(ProdottoMenuDTO prodottoMenuDTO);
    ProdottoMenuDTO updateProdottoMenu(Long id, ProdottoMenuDTO prodottoMenuDTO);
    void deleteProdottoMenu(Long id);
    SpettacoloDTO updateSpettacoloPrezzoBase(Long idSpettacolo, double nuovoPrezzoBase);
}