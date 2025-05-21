package org.elis.service;

import java.util.List;
import org.elis.dto.AttoreDTO;

public interface AttoreService 
{
    List<AttoreDTO> getAllAttori();
    AttoreDTO getAttoreById(Long id);
    AttoreDTO createAttore(AttoreDTO attoreDTO);
    AttoreDTO updateAttore(Long id, AttoreDTO attoreDTO);
    void deleteAttore(Long id);
    List<AttoreDTO> getAttoriByNome(String nome);
    List<AttoreDTO> getAttoriByCognome(String cognome);
}