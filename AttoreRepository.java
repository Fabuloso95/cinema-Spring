package org.elis.repository;

import java.util.List;
import java.util.Optional;

import org.elis.model.Attore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttoreRepository extends JpaRepository<Attore, Long> 
{
    List<Attore> findByNomeAndCognome(String nome, String cognome);
    Optional<Attore> findById(Long id);
    List<Attore> findAll();
    List<Attore> findByNomeContainingIgnoreCase(String nome);
    List<Attore> findByCognomeContainingIgnoreCase(String cognome);
}