package org.elis.service;

import java.util.List;
import org.elis.dto.TesseraFedeltaDTO;

public interface TesseraFedeltaService
{
    List<TesseraFedeltaDTO> getAllTessereFedelta();
    TesseraFedeltaDTO getTesseraFedeltaById(Long id);
    TesseraFedeltaDTO createTesseraFedelta(TesseraFedeltaDTO tesseraFedeltaDTO);
    TesseraFedeltaDTO updateTesseraFedelta(Long id, TesseraFedeltaDTO tesseraFedeltaDTO);
    void deleteTesseraFedelta(Long id);
}