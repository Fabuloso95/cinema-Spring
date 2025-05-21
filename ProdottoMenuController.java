package org.elis.controller;

import java.util.List;
import org.elis.dto.ProdottoMenuDTO;
import org.elis.facade.ProdottoMenuFacade;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/menu")
public class ProdottoMenuController 
{
    private final ProdottoMenuFacade menuFacade;

    public ProdottoMenuController(ProdottoMenuFacade menuFacade) 
    {
        this.menuFacade = menuFacade;
    }

    @GetMapping
    public ResponseEntity<List<ProdottoMenuDTO>> getAllMenus() 
    {
        List<ProdottoMenuDTO> menusDTO = menuFacade.getAllMenus();
        return new ResponseEntity<>(menusDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoMenuDTO> getMenuById(@PathVariable Long id) 
    {
        ProdottoMenuDTO menuDTO = menuFacade.getMenuById(id);
        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @PostMapping("/{idMenu}/prenota")
    public ResponseEntity<Void> prenotaProdottoMenu(@PathVariable Long idMenu, @Valid @RequestParam int quantita, @RequestParam Long idUtente) 
    {
        menuFacade.prenotaProdottoMenu(idUtente, idMenu, quantita);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdottoMenuDTO> createMenu(@RequestBody ProdottoMenuDTO prodottoMenuDTO) 
    {
        ProdottoMenuDTO createdMenuDTO = menuFacade.createMenu(prodottoMenuDTO);
        return new ResponseEntity<>(createdMenuDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdottoMenuDTO> updateMenu(@PathVariable Long id, @RequestBody ProdottoMenuDTO prodottoMenuDTO) 
    {
        ProdottoMenuDTO updatedMenuDTO = menuFacade.updateMenu(id, prodottoMenuDTO);
        return new ResponseEntity<>(updatedMenuDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) 
    {
        menuFacade.deleteMenu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}