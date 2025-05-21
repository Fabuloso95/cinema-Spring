package org.elis.repository;

import org.elis.model.Cliente;
import org.elis.model.TesseraFedelta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface TesseraFedeltaRepository extends JpaRepository<TesseraFedelta, Long> 
{
    Optional<TesseraFedelta> findByCodiceTesseraIgnoreCase(String codiceTessera);
    Optional<TesseraFedelta> findByCliente(Cliente cliente);
    Optional<TesseraFedelta> findByClienteId(Long clienteId);
    @Query("SELECT tf FROM TesseraFedelta tf ORDER BY tf.puntiAttuali DESC")
    List<TesseraFedelta> findTopByOrderByPuntiDesc(Pageable pageable);
}