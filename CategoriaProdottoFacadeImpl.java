// package org.elis.facade.impl;

package org.elis.facade.impl;

import org.elis.dto.CategoriaProdottoDTO;
import org.elis.facade.CategoriaProdottoFacade;
import org.elis.service.CategoriaProdottoService;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProdottoFacadeImpl implements CategoriaProdottoFacade 
{
    private final CategoriaProdottoService categoriaProdottoService;

    public CategoriaProdottoFacadeImpl(CategoriaProdottoService categoriaProdottoService) 
    {
        this.categoriaProdottoService = categoriaProdottoService;
    }

    @Override
    public List<CategoriaProdottoDTO> getAllCategorie() 
    {
        return categoriaProdottoService.getAllCategorie();
    }

    @Override
    public CategoriaProdottoDTO getCategoriaById(Long id) 
    {
        return categoriaProdottoService.getCategoriaById(id);
    }

    @Override
    public CategoriaProdottoDTO createCategoria(CategoriaProdottoDTO categoriaDTO) 
    {
        return categoriaProdottoService.createCategoria(categoriaDTO);
    }

    @Override
    public CategoriaProdottoDTO updateCategoria(Long id, CategoriaProdottoDTO categoriaDTO) 
    {
        return categoriaProdottoService.updateCategoria(id, categoriaDTO);
    }

    @Override
    public void deleteCategoria(Long id) 
    {
        categoriaProdottoService.deleteCategoria(id);
    }

    @Override
    public Optional<CategoriaProdottoDTO> findByNomeIgnoreCase(String nome)
    {
        return categoriaProdottoService.findByNomeIgnoreCase(nome);
    }
}