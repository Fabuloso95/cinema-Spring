package org.elis.controller;

import org.elis.dto.ClienteDTO;
import org.elis.dto.ProfiloUtenteDTO;
import org.elis.dto.StoricoAcquistiDTO;
import org.elis.facade.ClienteFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    private final ClienteFacade clienteFacade;

    public ClienteController(ClienteFacade clienteFacade) 
    {
        this.clienteFacade = clienteFacade;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClienti() 
    {
        List<ClienteDTO> clientiDTOs = clienteFacade.getAllClienti();
        return ResponseEntity.ok(clientiDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) 
    {
        ClienteDTO clienteDTO = clienteFacade.getClienteById(id);
        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) 
    {
        ClienteDTO nuovoClienteDTO = clienteFacade.createCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoClienteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) 
    {
        ClienteDTO clienteAggiornatoDTO = clienteFacade.updateCliente(id, clienteDTO);
        return ResponseEntity.ok(clienteAggiornatoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) 
    {
        clienteFacade.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/profilo")
    public ResponseEntity<ProfiloUtenteDTO> updateProfilo(@PathVariable Long id, @RequestBody ProfiloUtenteDTO profiloDTO)
    {
        ProfiloUtenteDTO updatedProfiloDTO = clienteFacade.updateProfilo(id, profiloDTO);
        return ResponseEntity.ok(updatedProfiloDTO);
    }

    @GetMapping("/{id}/storico-acquisti")
    public ResponseEntity<StoricoAcquistiDTO> getStoricoAcquisti(@PathVariable Long id) 
    {
        StoricoAcquistiDTO storicoDTO = clienteFacade.getStoricoAcquisti(id);
        return ResponseEntity.ok(storicoDTO);
    }

     @GetMapping("/username/{username}")
     public ResponseEntity<ClienteDTO> getClienteByUsername(@PathVariable String username) 
     {
         return clienteFacade.findByUsername(username)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
     }
}