package org.elis.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.elis.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> 
{
	Optional<Cliente> findById(Long id);
    Optional<Cliente> findByUsername(String username);
    Optional<Cliente> findByTesseraFedeltaCodiceTessera(String codiceTessera);
    List<Cliente> findByDataNascitaBefore(LocalDate data);

    @Query("SELECT c FROM Cliente c WHERE c.tesseraFedelta IS NOT NULL")
    List<Cliente> findByTesseraFedeltaIsNotNull();

    @Query("SELECT c FROM Cliente c WHERE c.tesseraFedelta IS NULL")
    List<Cliente> findByTesseraFedeltaIsNull();
}