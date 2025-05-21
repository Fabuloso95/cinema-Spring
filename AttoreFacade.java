package org.elis.facade;

import org.elis.dto.AttoreDTO;
import java.util.List;

public interface AttoreFacade 
{
    List<AttoreDTO> getAllAttori();
    AttoreDTO getAttoreById(Long id);
    AttoreDTO createAttore(AttoreDTO attoreDTO);
    AttoreDTO updateAttore(Long id, AttoreDTO attoreDTO);
    void deleteAttore(Long id);
    List<AttoreDTO> getAttoriByNome(String nome);
    List<AttoreDTO> getAttoriByCognome(String cognome);
}