package org.elis.controller;

import org.elis.dto.CategoriaProdottoDTO;
import org.elis.facade.CategoriaProdottoFacade;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorie-prodotto")
public class CategoriaProdottoController 
{
    private final CategoriaProdottoFacade categoriaProdottoFacade;

    public CategoriaProdottoController(CategoriaProdottoFacade categoriaProdottoFacade) 
    {
        this.categoriaProdottoFacade = categoriaProdottoFacade;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProdottoDTO>> getAllCategorie() 
    {
        List<CategoriaProdottoDTO> categorieDTO = categoriaProdottoFacade.getAllCategorie();
        return ResponseEntity.ok(categorieDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdottoDTO> getCategoriaById(@PathVariable Long id) 
    {
        CategoriaProdottoDTO categoriaDTO = categoriaProdottoFacade.getCategoriaById(id);
        return ResponseEntity.ok(categoriaDTO);
    }

    @PostMapping
    public ResponseEntity<CategoriaProdottoDTO> createCategoria(@RequestBody CategoriaProdottoDTO categoriaDTO) 
    {
        CategoriaProdottoDTO createdCategoriaDTO = categoriaProdottoFacade.createCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoriaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProdottoDTO> updateCategoria(@PathVariable Long id, @RequestBody CategoriaProdottoDTO categoriaDTO) 
    {
        CategoriaProdottoDTO updatedCategoriaDTO = categoriaProdottoFacade.updateCategoria(id, categoriaDTO);
        return ResponseEntity.ok(updatedCategoriaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) 
    {
        categoriaProdottoFacade.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<CategoriaProdottoDTO> getCategoriaByNome(@PathVariable String nome)
    {
        return categoriaProdottoFacade.findByNomeIgnoreCase(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}