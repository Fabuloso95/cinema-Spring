package org.elis.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import org.elis.dto.CategoriaProdottoDTO;
import org.elis.model.CategoriaProdotto;
import org.elis.repository.CategoriaProdottoRepository;
import org.elis.service.CategoriaProdottoService;
import org.elis.exceptionhandler.exception.RisorsaNonTrovataException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaProdottoServiceImpl implements CategoriaProdottoService
{
    private final CategoriaProdottoRepository categoriaProdottoRepository;
    private final ModelMapper modelMapper;

    public CategoriaProdottoServiceImpl(CategoriaProdottoRepository categoriaProdottoRepository, ModelMapper modelMapper)
    {
        this.categoriaProdottoRepository = categoriaProdottoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoriaProdottoDTO> getAllCategorie()
    {
        List<CategoriaProdotto> categorie = categoriaProdottoRepository.findAll();
        return categorie.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaProdottoDTO getCategoriaById(Long id)
    {
        CategoriaProdotto categoria = categoriaProdottoRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Categoria Prodotto non trovata con id: " + id));
        return mapEntityToDTO(categoria);
    }

    @Override
    @Transactional
    public CategoriaProdottoDTO createCategoria(CategoriaProdottoDTO categoriaDTO)
    {
        CategoriaProdotto categoria = mapDTOToEntity(categoriaDTO);
        categoria = categoriaProdottoRepository.save(categoria);
        return mapEntityToDTO(categoria);
    }

    @Override
    @Transactional
    public CategoriaProdottoDTO updateCategoria(Long id, CategoriaProdottoDTO categoriaDTO)
    {
        CategoriaProdotto existingCategoria = categoriaProdottoRepository.findById(id)
                .orElseThrow(() -> new RisorsaNonTrovataException("Categoria Prodotto non trovata con id: " + id));

        updateEntityFromDTO(existingCategoria, categoriaDTO);

        CategoriaProdotto updatedCategoria = categoriaProdottoRepository.save(existingCategoria);
        return mapEntityToDTO(updatedCategoria);
    }

    @Override
    @Transactional
    public void deleteCategoria(Long id)
    {
        categoriaProdottoRepository.deleteById(id);
    }

    @Override
    public Optional<CategoriaProdottoDTO> findByNomeIgnoreCase(String nome) 
    {
        Optional<CategoriaProdotto> categoria = categoriaProdottoRepository.findByNomeIgnoreCase(nome);
        return categoria.map(this::mapEntityToDTO);
    }

    private CategoriaProdottoDTO mapEntityToDTO(CategoriaProdotto categoria) 
    {
        if (categoria == null) return null;
        return modelMapper.map(categoria, CategoriaProdottoDTO.class);
    }

    private CategoriaProdotto mapDTOToEntity(CategoriaProdottoDTO categoriaDTO)
    {
        if (categoriaDTO == null) return null;
        return modelMapper.map(categoriaDTO, CategoriaProdotto.class);
    }

    private void updateEntityFromDTO(CategoriaProdotto existingCategoria, CategoriaProdottoDTO categoriaDTO)
    {
         if (existingCategoria == null || categoriaDTO == null) return;
         modelMapper.map(categoriaDTO, existingCategoria);
    }
}