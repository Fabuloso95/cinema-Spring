package org.elis.mapper;

import org.elis.dto.BigliettoDTO;
import org.elis.model.Biglietto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BigliettoMapper 
{
    private final ModelMapper modelMapper;

    public BigliettoMapper(ModelMapper modelMapper) 
    {
        this.modelMapper = modelMapper;
    }

    public BigliettoDTO toDTO(Biglietto biglietto) 
    {
        return modelMapper.map(biglietto, BigliettoDTO.class);
    }

    public Biglietto toEntity(BigliettoDTO bigliettoDTO) 
    {
        return modelMapper.map(bigliettoDTO, Biglietto.class);
    }
}