package org.elis.service;

import org.elis.dto.CategoriaProdottoDTO;
import java.util.List;
import java.util.Optional;

public interface CategoriaProdottoService 
{
    List<CategoriaProdottoDTO> getAllCategorie();
    CategoriaProdottoDTO getCategoriaById(Long id);
    CategoriaProdottoDTO createCategoria(CategoriaProdottoDTO categoriaDTO);
    CategoriaProdottoDTO updateCategoria(Long id, CategoriaProdottoDTO categoriaDTO);
    void deleteCategoria(Long id);
    Optional<CategoriaProdottoDTO> findByNomeIgnoreCase(String nome);
}