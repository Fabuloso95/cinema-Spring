package org.elis.repository;

import org.elis.model.Film;
import org.elis.model.Sala;
import org.elis.model.Spettacolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpettacoloRepository extends JpaRepository<Spettacolo, Long> 
{
    List<Spettacolo> findByFilm(Film film);
    List<Spettacolo> findBySala(Sala sala);
    List<Spettacolo> findByDataOraBetween(LocalDateTime dataInizio, LocalDateTime dataFine);
    List<Spettacolo> findByDataOraAfter(LocalDateTime data);
    List<Spettacolo> findByDataOraBefore(LocalDateTime data);

    @Query("SELECT s FROM Spettacolo s WHERE DATE(s.dataOra) = :data")
    List<Spettacolo> findByData(@Param("data") LocalDate data);

    List<Spettacolo> findByFilmId(Long filmId);
    List<Spettacolo> findBySalaId(Long salaId);

    @Query("SELECT s FROM Spettacolo s WHERE s.film.id = :filmId AND DATE(s.dataOra) = :data")
    List<Spettacolo> findByFilmIdAndData(@Param("filmId") Long filmId, @Param("data") LocalDate data);
}