package org.elis.facade.impl;

import org.elis.dto.*;
import org.elis.facade.ClienteFacade;
import org.elis.service.ClienteService;
import org.springframework.stereotype.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteFacadeImpl implements ClienteFacade 
{
    private final ClienteService clienteService;

    public ClienteFacadeImpl(ClienteService clienteService) 
    {
        this.clienteService = clienteService;
    }

    @Override
    public List<ClienteDTO> getAllClienti() 
    {
        return clienteService.getAllClienti();
    }

    @Override
    public ClienteDTO getClienteById(Long id) 
    {
        return clienteService.getClienteById(id);
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
    public ProfiloUtenteDTO updateProfilo(Long idCliente, ProfiloUtenteDTO profiloDTO) 
    {
        return clienteService.updateProfilo(idCliente, profiloDTO);
    }

    @Override
    public StoricoAcquistiDTO getStoricoAcquisti(Long idCliente) 
    {
        return clienteService.getStoricoAcquisti(idCliente);
    }

    @Override
    public Optional<ClienteDTO> findByUsername(String username) 
    {
        return clienteService.findByUsername(username);
    }

    @Override
    public Optional<ClienteDTO> findByTesseraFedeltaCodiceTessera(String codiceTessera) 
    {
        return clienteService.findByTesseraFedeltaCodiceTessera(codiceTessera);
    }

    @Override
    public List<ClienteDTO> findByDataNascitaBefore(LocalDate data) 
    {
        return clienteService.findByDataNascitaBefore(data);
    }

    @Override
    public List<ClienteDTO> findByTesseraFedeltaIsNotNull() 
    {
        return clienteService.findByTesseraFedeltaIsNotNull();
    }

    @Override
    public List<ClienteDTO> findByTesseraFedeltaIsNull() 
    {
        return clienteService.findByTesseraFedeltaIsNull();
    }
}