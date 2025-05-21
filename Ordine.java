package org.elis.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Ordine 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @Column(name = "data_ordine")
    private Date dataOrdine;

    @Column(name = "importo_totale")
    private double importoTotale;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DettaglioOrdine> dettagliOrdine = new ArrayList<>();

    @Column(name = "stato_ordine")
    @Enumerated(EnumType.STRING)
    private StatoOrdine statoOrdine;

    public enum StatoOrdine 
    {
        CREATO,
        PAGATO,
        IN_PREPARAZIONE,
        PRONTO,
        CONSEGNATO,
        CHIUSO,
        ANNULLATO
    }
}