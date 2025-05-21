package org.elis.repository;

import java.util.Optional;
import org.elis.model.Ordine;
import org.elis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> 
{
    Optional<Ordine> findOrdineAttivoByUtente(Utente utente);
}