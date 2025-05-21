package org.elis.facade;

import java.util.List;
import java.util.Optional;
import org.elis.dto.AdminDTO;
import org.elis.dto.ClienteDTO;
import org.elis.dto.ImpiegatoDTO;
import org.elis.dto.SalaDTO;

public interface AdminFacade 
{
	List<AdminDTO> getAllAdmins();
    AdminDTO getAdminById(Long id);
    AdminDTO createAdmin(AdminDTO adminDTO);
    AdminDTO updateAdmin(Long id, AdminDTO adminDTO);
    void deleteAdmin(Long id);
    Optional<AdminDTO> findByUsername(String username);
    ImpiegatoDTO createImpiegato(ImpiegatoDTO impiegatoDTO);
    ImpiegatoDTO updateImpiegato(Long id, ImpiegatoDTO impiegatoDTO);
    void deleteImpiegato(Long id);
    ClienteDTO createCliente(ClienteDTO clienteDTO);
    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);
    void deleteCliente(Long id);
    SalaDTO createSala(SalaDTO salaDTO);
    SalaDTO updateSala(Long id, SalaDTO salaDTO);
    void deleteSala(Long id);
}