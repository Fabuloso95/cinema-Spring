package org.elis.facade;

import org.elis.dto.SalaDTO;
import java.util.List;

public interface SalaFacade 
{
    List<SalaDTO> getAllSale();
    SalaDTO getSalaById(Long id);
    SalaDTO createSala(SalaDTO salaDTO);
    SalaDTO updateSala(Long id, SalaDTO salaDTO);
    void deleteSala(Long id);
    SalaDTO getSalaByNome(String nome);
    List<SalaDTO> getSaleByCapienza(int min);
    List<SalaDTO> getSaleUsufruibili();
}