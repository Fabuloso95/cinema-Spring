package org.elis.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdottoMenu 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeProdotto;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private double prezzo;

    @Column(nullable = false)
    private String categoria;
    
    @Column(nullable = false)
    private boolean disponibile;
    
    @Column(nullable = false)
    private int quantitaDisponibile;
}