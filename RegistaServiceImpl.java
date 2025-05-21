package org.elis.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.elis.dto.RegistaDTO;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.elis.model.Regista;
import org.elis.repository.RegistaRepository;
import org.elis.service.RegistaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistaServiceImpl implements RegistaService
{
    private final ModelMapper modelMapper;
    private final RegistaRepository registaRepository;

    public RegistaServiceImpl(RegistaRepository registaRepository, ModelMapper modelMapper)
    {
        this.registaRepository = registaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RegistaDTO> getAllRegisti()
    {
        List<Regista> registi = registaRepository.findAll();
        return mapRegistaListToDTOList(registi);
    }

    @Override
    public RegistaDTO getRegistaById(Long id)
    {
        Regista regista = registaRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Regista non trovato con id: " + id));
        return mapRegistaToDTO(regista);
    }

    @Override
    @Transactional
    public RegistaDTO createRegista(RegistaDTO registaDTO)
    {
        Regista regista = mapRegistaDTOToEntity(registaDTO);
        regista = registaRepository.save(regista);
        return mapRegistaToDTO(regista);
    }

    @Override
    @Transactional
    public RegistaDTO updateRegista(Long id, RegistaDTO registaDTO)
    {
        Regista existingRegista = registaRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Regista non trovato con id: " + id));

        updateRegistaFromDTO(existingRegista, registaDTO);

        Regista updatedRegista = registaRepository.save(existingRegista);
        return mapRegistaToDTO(updatedRegista);
    }

    @Override
    @Transactional
    public void deleteRegista(Long id)
    {
        registaRepository.deleteById(id);
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

    private List<RegistaDTO> mapRegistaListToDTOList(List<Regista> registi) 
    {
        if (registi == null) return null;
        return registi.stream()
                .map(this::mapRegistaToDTO)
                .collect(Collectors.toList());
    }

    private Regista mapRegistaDTOToEntity(RegistaDTO dto) 
    {
        if (dto == null) return null;
        Regista regista = new Regista();
        regista.setNome(dto.getNome());
        regista.setCognome(dto.getCognome());
        return regista;
    }

    private void updateRegistaFromDTO(Regista existingRegista, RegistaDTO dto) 
    {
        if (existingRegista == null || dto == null) return;
        if (dto.getNome() != null) existingRegista.setNome(dto.getNome());
        if (dto.getCognome() != null) existingRegista.setCognome(dto.getCognome());
    }
    
    @Override
    public Optional<RegistaDTO> findByNomeAndCognome(String nome, String cognome) 
    {
        Optional<Regista> regista = registaRepository.findByNomeAndCognome(nome, cognome);
        return regista.map(this::mapEntityToDTO);
    }

    private RegistaDTO mapEntityToDTO(Regista regista)
    {
        if (regista == null) return null;
        return modelMapper.map(regista, RegistaDTO.class);
    }

    @SuppressWarnings("unused")
	private Regista mapDTOToEntity(RegistaDTO dto)
    {
        if (dto == null) return null;
        return modelMapper.map(dto, Regista.class);
    }

    @SuppressWarnings("unused")
	private void updateEntityFromDTO(Regista existingRegista, RegistaDTO dto) 
    {
        if (existingRegista == null || dto == null) return;
        modelMapper.map(dto, existingRegista);
    }
}