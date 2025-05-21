package org.elis.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.elis.dto.*;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.elis.model.*;
import org.elis.repository.*;
import org.elis.service.SpettacoloService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpettacoloServiceImpl implements SpettacoloService
{
    private final SpettacoloRepository spettacoloRepository;
    private final FilmRepository filmRepository;
    private final SalaRepository salaRepository;
    private final BigliettoRepository bigliettoRepository;
	private final ModelMapper modelMapper;

    public SpettacoloServiceImpl(SpettacoloRepository spettacoloRepository, FilmRepository filmRepository, SalaRepository salaRepository, BigliettoRepository bigliettoRepository, ModelMapper modelMapper)
    {
        this.spettacoloRepository = spettacoloRepository;
        this.filmRepository = filmRepository;
        this.salaRepository = salaRepository;
        this.bigliettoRepository = bigliettoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SpettacoloDTO> getAllSpettacoli()
    {
        List<Spettacolo> spettacoli = spettacoloRepository.findAll();
        return mapSpettacoloListToDTOList(spettacoli);
    }

    @Override
    public SpettacoloDTO getSpettacoloById(Long id)
    {
        Spettacolo spettacolo = spettacoloRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Spettacolo non trovato con id: " + id));
        return mapSpettacoloToDTO(spettacolo);
    }

    @Override
    @Transactional
    public SpettacoloDTO createSpettacolo(SpettacoloCreationDTO spettacoloDTO)
    {
        Spettacolo nuovoSpettacolo = new Spettacolo();

        nuovoSpettacolo.setDataOra(spettacoloDTO.getDataOra());
        nuovoSpettacolo.setPrezzoBaseBiglietto(spettacoloDTO.getPrezzoBaseBiglietto());

        Film filmEsistente = filmRepository.findById(spettacoloDTO.getFilmId())
                                           .orElseThrow(() -> new RisorsaNonTrovataException("Film non trovato con ID: " + spettacoloDTO.getFilmId()));

        Sala salaEsistente = salaRepository.findById(spettacoloDTO.getSalaId())
                                           .orElseThrow(() -> new RisorsaNonTrovataException("Sala non trovata con ID: " + spettacoloDTO.getSalaId()));

        nuovoSpettacolo.setFilm(filmEsistente);
        nuovoSpettacolo.setSala(salaEsistente);

        Spettacolo savedSpettacolo = spettacoloRepository.save(nuovoSpettacolo);

        return mapSpettacoloToDTO(savedSpettacolo);
    }

    @Override
    @Transactional
    public SpettacoloDTO updateSpettacolo(Long id, SpettacoloUpdateDTO spettacoloDTO)
    {
        Spettacolo existingSpettacolo = spettacoloRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Spettacolo non trovato con id: " + id));

        if (spettacoloDTO.getDataOra() != null)
        {
             existingSpettacolo.setDataOra(spettacoloDTO.getDataOra());
        }
        existingSpettacolo.setPrezzoBaseBiglietto(spettacoloDTO.getPrezzoBaseBiglietto());

        if (spettacoloDTO.getFilmId() != null)
        {
            Film filmEsistente = filmRepository.findById(spettacoloDTO.getFilmId())
                                               .orElseThrow(() -> new RisorsaNonTrovataException("Film non trovato con ID: " + spettacoloDTO.getFilmId()));
            existingSpettacolo.setFilm(filmEsistente);
        }

        if (spettacoloDTO.getSalaId() != null)
        {
             Sala salaEsistente = salaRepository.findById(spettacoloDTO.getSalaId())
                                               .orElseThrow(() -> new RisorsaNonTrovataException("Sala non trovata con ID: " + spettacoloDTO.getSalaId()));
             existingSpettacolo.setSala(salaEsistente);
        }
        Spettacolo updatedSpettacolo = spettacoloRepository.save(existingSpettacolo);

        return mapSpettacoloToDTO(updatedSpettacolo);
    }

    @Override
    @Transactional
    public void deleteSpettacolo(Long id)
    {
        spettacoloRepository.deleteById(id);
    }

    @Override
    public DettaglioSpettacoloDTO getDettaglioSpettacolo(Long id)
    {
        Spettacolo spettacolo = spettacoloRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Spettacolo non trovato con id: " + id));

        DettaglioSpettacoloDTO dettaglioDTO = new DettaglioSpettacoloDTO();
        dettaglioDTO.setId(spettacolo.getId());
        dettaglioDTO.setDataOra(spettacolo.getDataOra());
        dettaglioDTO.setPrezzoBaseBiglietto(spettacolo.getPrezzoBaseBiglietto());

        if (spettacolo.getFilm() != null)
        {
            dettaglioDTO.setFilm(mapFilmToDTO(spettacolo.getFilm()));
        }

        if (spettacolo.getSala() != null)
        {
            dettaglioDTO.setSala(mapSalaToDTO(spettacolo.getSala()));
            int postiTotali = spettacolo.getSala().getCapienza();
            int bigliettiVenduti = bigliettoRepository.countBySpettacolo(spettacolo);
            dettaglioDTO.setPostiDisponibili(postiTotali - bigliettiVenduti);
        }
        else
        {
             dettaglioDTO.setPostiDisponibili(0);
        }
        return dettaglioDTO;
    }

    @Override
    public List<SpettacoloDTO> findByFilmId(Long filmId)
    {
        List<Spettacolo> spettacoli = spettacoloRepository.findByFilmId(filmId);
        return mapSpettacoloListToDTOList(spettacoli);
    }

    @Override
    public List<SpettacoloDTO> findBySalaId(Long salaId)
    {
        List<Spettacolo> spettacoli = spettacoloRepository.findBySalaId(salaId);
        return mapSpettacoloListToDTOList(spettacoli);
    }

    @Override
    public List<SpettacoloDTO> findByDataOraBetween(LocalDateTime start, LocalDateTime end)
    {
        List<Spettacolo> spettacoli = spettacoloRepository.findByDataOraBetween(start, end);
        return mapSpettacoloListToDTOList(spettacoli);
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
             dto.setFilm(modelMapper.map(spettacolo.getFilm(), FilmDTO.class));
        }

        if (spettacolo.getSala() != null)
        {
             dto.setSala(modelMapper.map(spettacolo.getSala(), SalaDTO.class));
        }
        return dto;
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

    private SalaDTO mapSalaToDTO(Sala sala)
    {
        if (sala == null) return null;
        SalaDTO dto = new SalaDTO();
        dto.setId(sala.getId());
        dto.setNome(sala.getNome());
        dto.setCapienza(sala.getCapienza());
        dto.setUsufruibile(sala.isUsufruibile());

        return dto;
    }

	@SuppressWarnings("unused")
	private List<AttoreDTO> mapAttoreListToDTOList(List<Attore> attori)
    {
        if (attori == null) return null;
        return attori.stream()
                     .map(this::mapAttoreToDTO)
                     .collect(Collectors.toList());
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

    private List<SpettacoloDTO> mapSpettacoloListToDTOList(List<Spettacolo> spettacoli)
     {
        if (spettacoli == null) return null;
        return spettacoli.stream()
                         .map(this::mapSpettacoloToDTO)
                         .collect(Collectors.toList());
     }
}