package org.elis.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "film")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String genere;

    @Column(nullable = false)
    private int durata;

    @Column(nullable = false)
    private int annoUscita;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_attore",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "attore_id")
    )
    private List<Attore> attori = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "regista_id", nullable = false)
    private Regista regista;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Spettacolo> spettacoli;

    public Film(String titolo, String genere, int durata, int annoUscita, Regista regista) 
    {
        this.titolo = titolo;
        this.genere = genere;
        this.durata = durata;
        this.annoUscita = annoUscita;
        this.regista = regista;
    }
}