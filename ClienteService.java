package org.elis.service;

import org.elis.dto.ClienteDTO;
import org.elis.dto.ProfiloUtenteDTO;
import org.elis.dto.StoricoAcquistiDTO;
import java.util.List;
import java.util.Optional;

public interface ClienteService
{
    List<ClienteDTO> getAllClienti();
    ClienteDTO getClienteById(Long id);
    ClienteDTO createCliente(ClienteDTO clienteDTO);
    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);
    void deleteCliente(Long id);
    Optional<ClienteDTO> findByUsername(String username);
    Optional<ClienteDTO> findByTesseraFedeltaCodiceTessera(String codiceTessera);
    List<ClienteDTO> findByDataNascitaBefore(java.time.LocalDate data);
    List<ClienteDTO> findByTesseraFedeltaIsNotNull();
    List<ClienteDTO> findByTesseraFedeltaIsNull();
    ProfiloUtenteDTO updateProfilo(Long idCliente, ProfiloUtenteDTO profiloDTO);
    StoricoAcquistiDTO getStoricoAcquisti(Long idCliente);
}
