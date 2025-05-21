package org.elis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "biglietti")
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Biglietto
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double prezzoFinale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spettacolo_id", nullable = false)
    @JsonIgnore
    private Spettacolo spettacolo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore
    private Cliente cliente;
    
    @Column(name = "sconto")
    private double sconto;

	public Biglietto(Long id, double prezzoFinale, Spettacolo spettacolo, Cliente cliente, double sconto) 
	{
		super();
		this.id = id;
		this.prezzoFinale = prezzoFinale;
		this.spettacolo = spettacolo;
		this.cliente = cliente;
		this.sconto = sconto;
	}
}