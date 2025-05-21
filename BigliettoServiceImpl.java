package org.elis.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.elis.dto.BigliettoDTO;
import org.elis.dto.SpettacoloDTO;
import org.elis.dto.ClienteDTO;
import org.elis.model.Biglietto;
import org.elis.model.Spettacolo;
import org.elis.model.Cliente;
import org.elis.repository.BigliettoRepository;
import org.elis.repository.SpettacoloRepository;
import org.elis.repository.ClienteRepository;
import org.elis.service.BigliettoService;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BigliettoServiceImpl implements BigliettoService
{
    private final BigliettoRepository bigliettoRepository;
    private final SpettacoloRepository spettacoloRepository;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;


    public BigliettoServiceImpl(BigliettoRepository bigliettoRepository, SpettacoloRepository spettacoloRepository, ClienteRepository clienteRepository, ModelMapper modelMapper)
    {
        this.bigliettoRepository = bigliettoRepository;
        this.spettacoloRepository = spettacoloRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BigliettoDTO> getAllBiglietti()
    {
        List<Biglietto> biglietti = bigliettoRepository.findAll();
        return biglietti.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BigliettoDTO getBigliettoById(Long id)
    {
        Biglietto biglietto = bigliettoRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Biglietto non trovato con id: " + id));
        return mapEntityToDTO(biglietto);
    }

    @Override
    @Transactional
    public BigliettoDTO createBiglietto(BigliettoDTO bigliettoDTO)
    {
        Biglietto biglietto = mapDTOToEntity(bigliettoDTO);

        if (bigliettoDTO.getSpettacolo() == null || bigliettoDTO.getSpettacolo().getId() == null)
        {
            throw new IllegalArgumentException("ID Spettacolo non fornito nel DTO di creazione biglietto");
        }
        Spettacolo spettacolo = spettacoloRepository.findById(bigliettoDTO.getSpettacolo().getId())
                .orElseThrow(() -> new RisorsaNonTrovataException("Spettacolo non trovato con ID: " + bigliettoDTO.getSpettacolo().getId()));
        biglietto.setSpettacolo(spettacolo);

        if (bigliettoDTO.getCliente() == null || bigliettoDTO.getCliente().getId() == null)
        {
             throw new IllegalArgumentException("ID Cliente non fornito nel DTO di creazione biglietto");
        }
        Cliente cliente = clienteRepository.findById(bigliettoDTO.getCliente().getId())
                .orElseThrow(() -> new RisorsaNonTrovataException("Cliente non trovato con ID: " + bigliettoDTO.getCliente().getId()));
        biglietto.setCliente(cliente);

        Biglietto nuovoBiglietto = bigliettoRepository.save(biglietto);

        return mapEntityToDTO(nuovoBiglietto);
    }

    @Override
    @Transactional
    public BigliettoDTO updateBiglietto(Long id, BigliettoDTO bigliettoDTO)
    {
        Biglietto existingBiglietto = bigliettoRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Biglietto non trovato con id: " + id));

        updateEntityFromDTO(existingBiglietto, bigliettoDTO);

        if (bigliettoDTO.getSpettacolo() != null && bigliettoDTO.getSpettacolo().getId() != null)
        {
            Spettacolo spettacolo = spettacoloRepository.findById(bigliettoDTO.getSpettacolo().getId())
                    .orElseThrow(() -> new RisorsaNonTrovataException("Spettacolo non trovato con ID: " + bigliettoDTO.getSpettacolo().getId()));
            existingBiglietto.setSpettacolo(spettacolo);
        }
        else
        {
             existingBiglietto.setSpettacolo(null);
        }

        if (bigliettoDTO.getCliente() != null && bigliettoDTO.getCliente().getId() != null)
        {
             Cliente cliente = clienteRepository.findById(bigliettoDTO.getCliente().getId())
                     .orElseThrow(() -> new RisorsaNonTrovataException("Cliente non trovato con ID: " + bigliettoDTO.getCliente().getId()));
             existingBiglietto.setCliente(cliente);
        }
        else
        {
             existingBiglietto.setCliente(null);
        }

        Biglietto updatedBiglietto = bigliettoRepository.save(existingBiglietto);
        return mapEntityToDTO(updatedBiglietto);
    }

    @Override
    @Transactional
    public void deleteBiglietto(Long id)
    {
        if (!bigliettoRepository.existsById(id))
        {
            throw new RisorsaNonTrovataException("Biglietto non trovato con id: " + id);
        }
        bigliettoRepository.deleteById(id);
    }


    private BigliettoDTO mapEntityToDTO(Biglietto biglietto)
    {
        if (biglietto == null) return null;
        BigliettoDTO dto = modelMapper.map(biglietto, BigliettoDTO.class);

        if (biglietto.getSpettacolo() != null)
        {
             dto.setSpettacolo(modelMapper.map(biglietto.getSpettacolo(), SpettacoloDTO.class));
        }

        if (biglietto.getCliente() != null)
        {
             dto.setCliente(modelMapper.map(biglietto.getCliente(), ClienteDTO.class));
        }
        return dto;
    }

     private Biglietto mapDTOToEntity(BigliettoDTO bigliettoDTO)
     {
         if (bigliettoDTO == null) return null;
         return modelMapper.map(bigliettoDTO, Biglietto.class);
     }

     private void updateEntityFromDTO(Biglietto existingBiglietto, BigliettoDTO bigliettoDTO)
     {
         if (existingBiglietto == null || bigliettoDTO == null) return;
         modelMapper.map(bigliettoDTO, existingBiglietto);
     }
}