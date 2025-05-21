package org.elis.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categorie_prodotto")
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class CategoriaProdotto 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String descrizione;

    public CategoriaProdotto(String nome, String descrizione) 
    {
        this.nome = nome;
        this.descrizione = descrizione;
    }
}