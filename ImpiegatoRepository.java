package org.elis.repository;

import org.elis.model.Impiegato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ImpiegatoRepository extends JpaRepository<Impiegato, Long> 
{
    Optional<Impiegato> findById(Long id);
    Optional<Impiegato> findByUsername(String username);
}