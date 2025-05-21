package org.elis.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.elis.dto.TesseraFedeltaDTO;
import org.elis.model.TesseraFedelta;
import org.elis.model.Cliente;
import org.elis.repository.TesseraFedeltaRepository;
import org.elis.repository.ClienteRepository;
import org.elis.service.TesseraFedeltaService;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TesseraFedeltaServiceImpl implements TesseraFedeltaService
{
    private final TesseraFedeltaRepository tesseraFedeltaRepository;
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public TesseraFedeltaServiceImpl(TesseraFedeltaRepository tesseraFedeltaRepository, ClienteRepository clienteRepository, ModelMapper modelMapper)
    {
        this.tesseraFedeltaRepository = tesseraFedeltaRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TesseraFedeltaDTO> getAllTessereFedelta()
    {
        List<TesseraFedelta> tessereFedelta = tesseraFedeltaRepository.findAll();
        return tessereFedelta.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TesseraFedeltaDTO getTesseraFedeltaById(Long id)
    {
        TesseraFedelta tesseraFedelta = tesseraFedeltaRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("TesseraFedelta non trovata con id: " + id));
        return mapEntityToDTO(tesseraFedelta);
    }

    @Override
    @Transactional
    public TesseraFedeltaDTO createTesseraFedelta(TesseraFedeltaDTO tesseraFedeltaDTO)
    {
        TesseraFedelta tesseraFedelta = modelMapper.map(tesseraFedeltaDTO, TesseraFedelta.class);

        if (tesseraFedeltaDTO.getIdCliente() == null) 
        {
            throw new IllegalArgumentException("ID Cliente non fornito nel DTO di creazione tessera fedelta");
        }
        Cliente cliente = clienteRepository.findById(tesseraFedeltaDTO.getIdCliente())
                .orElseThrow(() -> new RisorsaNonTrovataException("Cliente non trovato con ID: " + tesseraFedeltaDTO.getIdCliente()));
        tesseraFedelta.setCliente(cliente);

        TesseraFedelta saved = tesseraFedeltaRepository.save(tesseraFedelta);
        return mapEntityToDTO(saved);
    }

    @Override
    @Transactional
    public TesseraFedeltaDTO updateTesseraFedelta(Long id, TesseraFedeltaDTO tesseraFedeltaDTO)
    {
        TesseraFedelta existingTesseraFedelta = tesseraFedeltaRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("TesseraFedelta non trovata con id: " + id));

        modelMapper.map(tesseraFedeltaDTO, existingTesseraFedelta);

        if (tesseraFedeltaDTO.getIdCliente() != null) 
        {
            Cliente cliente = clienteRepository.findById(tesseraFedeltaDTO.getIdCliente())
                    .orElseThrow(() -> new RisorsaNonTrovataException("Cliente non trovato con ID: " + tesseraFedeltaDTO.getIdCliente()));
            existingTesseraFedelta.setCliente(cliente);
        } 
        else 
        {
            existingTesseraFedelta.setCliente(null);
        }

        TesseraFedelta updated = tesseraFedeltaRepository.save(existingTesseraFedelta);
        return mapEntityToDTO(updated);
    }

    @Override
    @Transactional
    public void deleteTesseraFedelta(Long id)
    {
        if (!tesseraFedeltaRepository.existsById(id))
        {
            throw new RisorsaNonTrovataException("TesseraFedelta non trovata con id: " + id);
        }
        tesseraFedeltaRepository.deleteById(id);
    }

    private TesseraFedeltaDTO mapEntityToDTO(TesseraFedelta tesseraFedelta)
    {
        if (tesseraFedelta == null) return null;
        TesseraFedeltaDTO dto = modelMapper.map(tesseraFedelta, TesseraFedeltaDTO.class);
        if (tesseraFedelta.getCliente() != null) {
            dto.setIdCliente(tesseraFedelta.getCliente().getId());
            dto.setUsernameCliente(tesseraFedelta.getCliente().getUsername());
        }
        return dto;
    }

    @SuppressWarnings("unused")
	private TesseraFedelta mapDTOToEntity(TesseraFedeltaDTO tesseraFedeltaDTO)
    {
        if (tesseraFedeltaDTO == null) return null;
        return modelMapper.map(tesseraFedeltaDTO, TesseraFedelta.class);
    }
}