package org.elis.facade.impl;

import org.elis.dto.AdminDTO;
import org.elis.dto.ClienteDTO;
import org.elis.dto.ImpiegatoDTO;
import org.elis.dto.SalaDTO;
import org.elis.facade.AdminFacade;
import org.elis.service.AdminService;
import org.elis.service.ClienteService;
import org.elis.service.ImpiegatoService;
import org.elis.service.SalaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminFacadeImpl implements AdminFacade 
{

    private final AdminService adminService;
    private final ImpiegatoService impiegatoService;
    private final ClienteService clienteService;
    private final SalaService salaService;

    public AdminFacadeImpl(AdminService adminService, ImpiegatoService impiegatoService, ClienteService clienteService, SalaService salaService) 
    {
        this.adminService = adminService;
        this.impiegatoService = impiegatoService;
        this.clienteService = clienteService;
        this.salaService = salaService;
    }

    @Override
    public List<AdminDTO> getAllAdmins() 
    {
        return adminService.getAllAdmins();
    }

    @Override
    public AdminDTO getAdminById(Long id) 
    {
        return adminService.getAdminById(id);
    }

    @Override
    public AdminDTO createAdmin(AdminDTO adminDTO) 
    {
        return adminService.createAdmin(adminDTO);
    }

    @Override
    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO)
    {
        return adminService.updateAdmin(id, adminDTO);
    }

    @Override
    public void deleteAdmin(Long id) 
    {
        adminService.deleteAdmin(id);
    }

    @Override
    public Optional<AdminDTO> findByUsername(String username)
    {
        return adminService.findByUsername(username);
    }

    @Override
    public ImpiegatoDTO createImpiegato(ImpiegatoDTO impiegatoDTO)
    {
        return impiegatoService.createImpiegato(impiegatoDTO);
    }

    @Override
    public ImpiegatoDTO updateImpiegato(Long id, ImpiegatoDTO impiegatoDTO) 
    {
        return impiegatoService.updateImpiegato(id, impiegatoDTO);
    }

    @Override
    public void deleteImpiegato(Long id) 
    {
        impiegatoService.deleteImpiegato(id);
    }

    @Override
    public ClienteDTO createCliente(ClienteDTO clienteDTO)
    {
        return clienteService.createCliente(clienteDTO);
    }

    @Override
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) 
    {
        return clienteService.updateCliente(id, clienteDTO);
    }

    @Override
    public void deleteCliente(Long id)
    {
        clienteService.deleteCliente(id);
    }

    @Override
    public SalaDTO createSala(SalaDTO salaDTO) 
    {
        return salaService.createSala(salaDTO);
    }

    @Override
    public SalaDTO updateSala(Long id, SalaDTO salaDTO) 
    {
        return salaService.updateSala(id, salaDTO);
    }

    @Override
    public void deleteSala(Long id) 
    {
        salaService.deleteSala(id);
    }
}