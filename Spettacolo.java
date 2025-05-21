package org.elis.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "spettacoli")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Spettacolo 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataOra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    @JsonIgnore
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    @JsonIgnore
    private Sala sala;

    @Column(nullable = false)
    private double prezzoBaseBiglietto;

     @OneToMany(mappedBy = "spettacolo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Biglietto> biglietti;

     public Spettacolo(LocalDateTime dataOra, Film film, Sala sala, double prezzoBaseBiglietto) 
     {
        this.dataOra = dataOra;
        this.film = film;
        this.sala = sala;
        this.prezzoBaseBiglietto = prezzoBaseBiglietto;
    }
}