package org.elis.controller;

import org.elis.dto.AdminDTO;
import org.elis.dto.ImpiegatoDTO;
import org.elis.dto.ClienteDTO;
import org.elis.dto.SalaDTO;
import org.elis.facade.AdminFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminFacade adminFacade;

    public AdminController(AdminFacade adminFacade) 
    {
        this.adminFacade = adminFacade;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() 
    {
        List<AdminDTO> adminDTOs = adminFacade.getAllAdmins();
        return ResponseEntity.ok(adminDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) 
    {
        AdminDTO adminDTO = adminFacade.getAdminById(id);
        return ResponseEntity.ok(adminDTO);
    }

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO)
    {
        AdminDTO nuovoAdminDTO = adminFacade.createAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoAdminDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO) 
    {
        AdminDTO adminAggiornatoDTO = adminFacade.updateAdmin(id, adminDTO);
        return ResponseEntity.ok(adminAggiornatoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id)
    {
        adminFacade.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/impiegati")
    public ResponseEntity<ImpiegatoDTO> createImpiegato(@RequestBody ImpiegatoDTO impiegatoDTO) 
    {
        ImpiegatoDTO createdImpiegato = adminFacade.createImpiegato(impiegatoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdImpiegato);
    }

    @PutMapping("/impiegati/{id}")
    public ResponseEntity<ImpiegatoDTO> updateImpiegato(@PathVariable Long id, @RequestBody ImpiegatoDTO impiegatoDTO) 
    {
        ImpiegatoDTO updatedImpiegato = adminFacade.updateImpiegato(id, impiegatoDTO);
        return ResponseEntity.ok(updatedImpiegato);
    }

    @DeleteMapping("/impiegati/{id}")
    public ResponseEntity<Void> deleteImpiegato(@PathVariable Long id) 
    {
        adminFacade.deleteImpiegato(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/clienti")
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) 
    {
        ClienteDTO createdCliente = adminFacade.createCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @PutMapping("/clienti/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) 
    {
        ClienteDTO updatedCliente = adminFacade.updateCliente(id, clienteDTO);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/clienti/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) 
    {
        adminFacade.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sale")
    public ResponseEntity<SalaDTO> createSala(@RequestBody SalaDTO salaDTO) 
    {
        SalaDTO createdSala = adminFacade.createSala(salaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSala);
    }

    @PutMapping("/sale/{id}")
    public ResponseEntity<SalaDTO> updateSala(@PathVariable Long id, @RequestBody SalaDTO salaDTO)
    {
        SalaDTO updatedSala = adminFacade.updateSala(id, salaDTO);
        return ResponseEntity.ok(updatedSala);
    }

    @DeleteMapping("/sale/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable Long id) 
    {
        adminFacade.deleteSala(id);
        return ResponseEntity.noContent().build();
    }
}