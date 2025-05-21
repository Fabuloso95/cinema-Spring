package org.elis.repository;

import java.util.*;
import org.elis.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistaRepository extends JpaRepository<Regista, Long> 
{
    Optional<Regista> findById(Long id);
    Optional<Regista> findByNomeAndCognome(String nome, String cognome);
    List<Film> findByFilms(List<Film> films);
}