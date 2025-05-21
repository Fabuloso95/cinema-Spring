package org.elis.repository;

import org.elis.model.Biglietto;
import org.elis.model.Spettacolo;
import org.elis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BigliettoRepository extends JpaRepository<Biglietto, Long> 
{
	Optional<Biglietto> findById(Long id);
    List<Biglietto> findBySpettacolo(Spettacolo spettacolo);
    List<Biglietto> findByCliente(Utente cliente);
    List<Biglietto> findBySpettacoloId(Long spettacoloId);
    List<Biglietto> findByClienteId(Long clienteId);
    long countBySpettacoloId(Long spettacoloId);
	List<Biglietto> findBySpettacoloDataOraBetween(LocalDateTime atStartOfDay, LocalDateTime atTime);
	int countBySpettacolo(Spettacolo spettacolo);
}