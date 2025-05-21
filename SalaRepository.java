package org.elis.repository;

import org.elis.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> 
{
    Optional<Sala> findById(Long id);
    Optional<Sala> findByNomeIgnoreCase(String nome);
    List<Sala> findByCapienzaGreaterThanEqual(int capienza);
    List<Sala> findByIsUsufruibile(boolean isUsufruibile);
}