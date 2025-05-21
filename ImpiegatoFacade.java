package org.elis.facade;

import org.elis.dto.ImpiegatoDTO;
import org.elis.dto.FilmDTO;
import org.elis.dto.SpettacoloDTO;
import org.elis.dto.ProdottoMenuDTO;
import org.elis.dto.SpettacoloCreationDTO;
import org.elis.dto.SpettacoloUpdateDTO;
import java.util.List;
import java.util.Optional;

public interface ImpiegatoFacade 
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
    SpettacoloDTO updateSpettacoloPrezzoBase(Long idSpettacolo, double nuovoPrezzoBase);
    ProdottoMenuDTO createProdottoMenu(ProdottoMenuDTO prodottoMenuDTO);
    ProdottoMenuDTO updateProdottoMenu(Long id, ProdottoMenuDTO prodottoMenuDTO);
    void deleteProdottoMenu(Long id);
}