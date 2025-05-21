package org.elis.service.impl;

import org.elis.dto.*;
import org.elis.model.*;
import org.elis.repository.*;
import org.elis.service.FilmService;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService
{
    private final FilmRepository filmRepository;
    private final AttoreRepository attoreRepository;
    private final RegistaRepository registaRepository;
    private final SpettacoloRepository spettacoloRepository;
    @SuppressWarnings("unused")
	private final ModelMapper modelMapper;

    public FilmServiceImpl(FilmRepository filmRepository, AttoreRepository attoreRepository, RegistaRepository registaRepository, SpettacoloRepository spettacoloRepository, ModelMapper modelMapper)
    {
        this.filmRepository = filmRepository;
        this.attoreRepository = attoreRepository;
        this.registaRepository = registaRepository;
        this.spettacoloRepository = spettacoloRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FilmDTO> getAllFilms()
    {
        List<Film> films = filmRepository.findAll();
        return films.stream()
                .map(this::mapFilmToDTO)
                .collect(Collectors.toList());
    } 

    @Override
    public FilmDTO getFilmById(Long id)
    {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Film non trovato con id: " + id));
        return mapFilmToDTO(film);
    }

    @Override
    @Transactional
    public FilmDTO createFilm(FilmDTO filmDTO)
    {
        Film film = new Film();
        film.setTitolo(filmDTO.getTitolo());
        film.setGenere(filmDTO.getGenere());
        film.setDurata(filmDTO.getDurata());
        film.setAnnoUscita(filmDTO.getAnnoUscita());

        if (filmDTO.getRegista() != null && filmDTO.getRegista().getId() != null)
        {
            Regista regista = registaRepository.findById(filmDTO.getRegista().getId())
                    .orElseThrow(() -> new RisorsaNonTrovataException("Regista non trovato con ID: " + filmDTO.getRegista().getId()));
            film.setRegista(regista);
        }
        if (filmDTO.getAttori() != null && !filmDTO.getAttori().isEmpty())
        {
             List<Attore> attori = filmDTO.getAttori().stream()
                     .map(attoreDTO ->
                     {
                         if (attoreDTO.getId() == null)
                         {
                             throw new IllegalArgumentException("Attore ID non fornito nel DTO di creazione film");
                         }
                         return attoreRepository.findById(attoreDTO.getId())
                                 .orElseThrow(() -> new RisorsaNonTrovataException("Attore non trovato con ID: " + attoreDTO.getId()));
                     })
                     .collect(Collectors.toList());
             film.setAttori(attori);
        }

        film = filmRepository.save(film);
        return mapFilmToDTO(film);
    }

    @Override
    @Transactional
    public FilmDTO updateFilm(Long id, FilmDTO filmDTO)
    {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Film non trovato con id: " + id));
        if (filmDTO.getTitolo() != null) film.setTitolo(filmDTO.getTitolo());
        if (filmDTO.getGenere() != null) film.setGenere(filmDTO.getGenere());
        if (filmDTO.getDurata() > 0) film.setDurata(filmDTO.getDurata());
        if (filmDTO.getAnnoUscita() > 0) film.setAnnoUscita(filmDTO.getAnnoUscita());
         if (filmDTO.getRegista() != null && filmDTO.getRegista().getId() != null)
         {
             Regista regista = registaRepository.findById(filmDTO.getRegista().getId())
                     .orElseThrow(() -> new RisorsaNonTrovataException("Regista non trovato con ID: " + filmDTO.getRegista().getId()));
             film.setRegista(regista);
         } else if (filmDTO.getRegista() != null && filmDTO.getRegista().getId() == null)
         {
             if (film.getRegista() != null)
             {
                  throw new IllegalArgumentException("Non è possibile rimuovere il regista da un film obbligatorio");
             }
         }
         if (filmDTO.getAttori() != null)
         {
              List<Attore> attoriAggiornati = filmDTO.getAttori().stream()
                      .map(attoreDTO ->
                      {
                          if (attoreDTO.getId() == null)
                          {
                             throw new IllegalArgumentException("Attore ID non fornito nel DTO di update film");
                          }
                          return attoreRepository.findById(attoreDTO.getId())
                                  .orElseThrow(() -> new RisorsaNonTrovataException("Attore non trovato con ID: " + attoreDTO.getId()));
                      })
                      .collect(Collectors.toList());

               film.getAttori().clear();
               film.getAttori().addAll(attoriAggiornati);
         }
        film = filmRepository.save(film);
        return mapFilmToDTO(film);
    }

    @Override
    @Transactional
    public void deleteFilm(Long id)
    {
         Film filmToDelete = filmRepository.findById(id)
                 .orElseThrow(() -> new RisorsaNonTrovataException("Film non trovato con id: " + id));
         filmToDelete.getAttori().forEach(attore -> attore.getFilms().remove(filmToDelete));
         filmToDelete.getAttori().clear();
         filmRepository.delete(filmToDelete);
    }

    @Override
    @Transactional
    public void addAttoreToFilm(Long filmId, Long attoreId)
    {
        Film film = filmRepository.findById(filmId)
                 .orElseThrow(() -> new RisorsaNonTrovataException("Film non trovato con id: " + filmId));
        Attore attore = attoreRepository.findById(attoreId)
                 .orElseThrow(() -> new RisorsaNonTrovataException("Attore non trovato con id: " + attoreId));

        if (!film.getAttori().contains(attore))
        {
            film.getAttori().add(attore);
            if (!attore.getFilms().contains(film))
            {
                 attore.getFilms().add(film);
            }
        }
    }

    @Override
    @Transactional
    public void removeAttoreFromFilm(Long filmId, Long attoreId)
    {
         Film film = filmRepository.findById(filmId)
                 .orElseThrow(() -> new RisorsaNonTrovataException("Film non trovato con id: " + filmId));
        Attore attore = attoreRepository.findById(attoreId)
                 .orElseThrow(() -> new RisorsaNonTrovataException("Attore non trovato con id: " + attoreId));

        if (film.getAttori().contains(attore))
        {
            film.getAttori().remove(attore);
             if (attore.getFilms().contains(film))
             {
                  attore.getFilms().remove(film);
             }
        }
    }

    @Override
    @Transactional
    public void setRegistaToFilm(Long filmId, Long registaId)
    {
         Film film = filmRepository.findById(filmId)
                 .orElseThrow(() -> new RisorsaNonTrovataException("Film non trovato con id: " + filmId));
        Regista regista = registaRepository.findById(registaId)
                 .orElseThrow(() -> new RisorsaNonTrovataException("Regista non trovato con id: " + registaId));

        film.setRegista(regista);
    }

    @Override
    public List<FilmConSpettacoliDTO> getFilmConSpettacoli()
    {
        List<Film> films = filmRepository.findAll();
        return films.stream()
                .map(film -> 
                {
                    FilmConSpettacoliDTO dto = new FilmConSpettacoliDTO();
                    dto.setFilm(mapFilmToDTO(film));
                    List<Spettacolo> spettacoli = spettacoloRepository.findByFilm(film);
                    if (spettacoli != null)
                    {
                        List<SpettacoloDTO> spettacoliDTO = spettacoli.stream()
                                .map(this::mapSpettacoloToDTO)
                                .collect(Collectors.toList());
                        dto.setSpettacoli(spettacoliDTO);
                    } 
                    else 
                    {
                         dto.setSpettacoli(new java.util.ArrayList<>());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FilmConSpettacoliDTO> getFilmConSpettacoliById(Long id) 
    {
         Optional<Film> filmOptional = filmRepository.findById(id);
         return filmOptional.map(film -> 
         {
             FilmConSpettacoliDTO dto = new FilmConSpettacoliDTO();
             dto.setFilm(mapFilmToDTO(film));
             List<Spettacolo> spettacoli = spettacoloRepository.findByFilm(film);
             if (spettacoli != null) 
             {
                 List<SpettacoloDTO> spettacoliDTO = spettacoli.stream()
                         .map(this::mapSpettacoloToDTO)
                         .collect(Collectors.toList());
                 dto.setSpettacoli(spettacoliDTO);
             } 
             else 
             {
                  dto.setSpettacoli(new java.util.ArrayList<>());
             }
             return dto;
         });
    }

    @Override
    public Optional<FilmDTO> findByTitoloIgnoreCase(String titolo) 
    {
        Optional<Film> film = filmRepository.findByTitoloIgnoreCase(titolo);
        return film.map(this::mapFilmToDTO);
    }

    @Override
    public List<FilmDTO> findByGenereIgnoreCase(String genere) 
    {
        List<Film> films = filmRepository.findByGenereIgnoreCase(genere);
        return films.stream()
                .map(this::mapFilmToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByAnnoUscita(int anno) 
    {
        List<Film> films = filmRepository.findByAnnoUscita(anno);
        return films.stream()
                .map(this::mapFilmToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByDurataBetween(int minDurata, int maxDurata) 
    {
        List<Film> films = filmRepository.findByDurataBetween(minDurata, maxDurata);
        return films.stream()
                .map(this::mapFilmToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByRegistaId(Long registaId) 
    {
        List<Film> films = filmRepository.findByRegistaId(registaId);
        return films.stream()
                .map(this::mapFilmToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByAttoriId(Long attoreId) 
    {
        List<Film> films = filmRepository.findByAttoriId(attoreId);
        return films.stream()
                .map(this::mapFilmToDTO)
                .collect(Collectors.toList());
    }

    private FilmDTO mapFilmToDTO(Film film)
    {
        if (film == null) return null;
        FilmDTO dto = new FilmDTO();
        dto.setId(film.getId());
        dto.setTitolo(film.getTitolo());
        dto.setGenere(film.getGenere());
        dto.setDurata(film.getDurata());
        dto.setAnnoUscita(film.getAnnoUscita());

        if (film.getAttori() != null)
        {
            dto.setAttori(film.getAttori().stream().map(this::mapAttoreToDTO).collect(Collectors.toList()));
        }

        if (film.getRegista() != null)
        {
            dto.setRegista(mapRegistaToDTO(film.getRegista()));
        }
        return dto;
    }

    private AttoreDTO mapAttoreToDTO(Attore attore)
    {
        if (attore == null) return null;
        AttoreDTO dto = new AttoreDTO();
        dto.setId(attore.getId());
        dto.setNome(attore.getNome());
        dto.setCognome(attore.getCognome());
        return dto;
    }

    private RegistaDTO mapRegistaToDTO(Regista regista)
    {
        if (regista == null) return null;
        RegistaDTO dto = new RegistaDTO();
        dto.setId(regista.getId());
        dto.setNome(regista.getNome());
        dto.setCognome(regista.getCognome());
        return dto;
    }

    private SpettacoloDTO mapSpettacoloToDTO(Spettacolo spettacolo)
    {
        if (spettacolo == null) return null;
        SpettacoloDTO dto = new SpettacoloDTO();
        dto.setId(spettacolo.getId());
        dto.setDataOra(spettacolo.getDataOra());
        dto.setPrezzoBaseBiglietto(spettacolo.getPrezzoBaseBiglietto());
        if (spettacolo.getFilm() != null) 
        {
             FilmDTO filmDTO = new FilmDTO();
             filmDTO.setId(spettacolo.getFilm().getId());
             filmDTO.setTitolo(spettacolo.getFilm().getTitolo());
             dto.setFilm(filmDTO);
        }
        if (spettacolo.getSala() != null)
        {
             SalaDTO salaDTO = new SalaDTO();
             salaDTO.setId(spettacolo.getSala().getId());
             salaDTO.setNome(spettacolo.getSala().getNome());
             dto.setSala(salaDTO);
        }
        return dto;
    }
}