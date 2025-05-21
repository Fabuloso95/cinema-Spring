package org.elis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class DettaglioOrdine 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private ProdottoMenu menu;

    private int quantita;

    @Column(name = "prezzo_unitario")
    private double prezzoUnitario;
}