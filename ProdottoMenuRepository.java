package org.elis.repository;

import org.elis.model.ProdottoMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdottoMenuRepository extends JpaRepository<ProdottoMenu, Long> 
{
    Optional<ProdottoMenu> findById(Long id);
    Optional<ProdottoMenu> findByNomeProdottoIgnoreCase(String nomeProdotto);
    List<ProdottoMenu> findByCategoriaIgnoreCase(String categoria);
    List<ProdottoMenu> findByPrezzoBetween(double minPrice, double maxPrice);
}