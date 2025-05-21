package org.elis.repository;

import org.elis.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> 
{
    Optional<Film> findByTitoloIgnoreCase(String titolo);
    List<Film> findByTitoloContainingIgnoreCase(String titolo);
    List<Film> findByGenereIgnoreCase(String genere);
    List<Film> findByGenereContainingIgnoreCase(String genere);
    List<Film> findByAnnoUscita(int anno);
    List<Film> findByAnnoUscitaGreaterThan(int anno);
    List<Film> findByAnnoUscitaLessThan(int anno);
    List<Film> findByDurataGreaterThan(int durata);
    List<Film> findByDurataLessThan(int durata);
    Optional<Film> findById(Long id);
    @Query("SELECT f FROM Film f JOIN f.attori a WHERE a.nome = :nomeAttore")
    List<Film> findByAttoreNome(@Param("nomeAttore") String nomeAttore);
    @Query("SELECT f FROM Film f WHERE f.regista.nome = :nomeRegista")
    List<Film> findByRegistaNome(@Param("nomeRegista") String nomeRegista);
	List<Film> findByDurataBetween(int minDurata, int maxDurata);
	List<Film> findByRegistaId(Long registaId);
	List<Film> findByAttoriId(Long attoreId);
}