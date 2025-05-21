package org.elis.repository;

import org.elis.model.CategoriaProdotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CategoriaProdottoRepository extends JpaRepository<CategoriaProdotto, Long>
{
    Optional<CategoriaProdotto> findById(Long id);
    Optional<CategoriaProdotto> findByNomeIgnoreCase(String nome);
    List<CategoriaProdotto> findByNomeContainingIgnoreCase(String nome);
    List<CategoriaProdotto> findAll();
}