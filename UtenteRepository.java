package org.elis.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.elis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> 
{
    Optional<Utente> findByUsername(String username);
    List<Utente> findByNomeContainingIgnoreCase(String nome);
    List<Utente> findByCognomeContainingIgnoreCase(String cognome);
    List<Utente> findByNomeContainingIgnoreCaseAndCognomeContainingIgnoreCase(String nome, String cognome);
    List<Utente> findByDataNascitaBefore(LocalDate data);
    List<Utente> findByDataNascitaAfter(LocalDate data);
    List<Utente> findByDataNascitaBetween(LocalDate dataInizio, LocalDate dataFine);
    boolean existsByUsername(String username);

    @Query("SELECT u FROM Utente u WHERE lower(u.nome) LIKE lower(concat('%', :searchTerm, '%')) OR lower(u.cognome) LIKE lower(concat('%', :searchTerm, '%')) OR lower(u.username) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Utente> searchUtenti(@Param("searchTerm") String searchTerm);
    List<Utente> findByNomeAndCognome(String nome, String cognome);
}