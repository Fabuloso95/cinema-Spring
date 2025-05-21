package org.elis.facade;

import org.elis.dto.CategoriaProdottoDTO;
import java.util.List;
import java.util.Optional;

public interface CategoriaProdottoFacade
{
    List<CategoriaProdottoDTO> getAllCategorie();
    CategoriaProdottoDTO getCategoriaById(Long id);
    CategoriaProdottoDTO createCategoria(CategoriaProdottoDTO categoriaDTO);
    CategoriaProdottoDTO updateCategoria(Long id, CategoriaProdottoDTO categoriaDTO);
    void deleteCategoria(Long id);
    Optional<CategoriaProdottoDTO> findByNomeIgnoreCase(String nome);
}