package org.elis.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BigliettoDTO 
{
    private Long id;
    @Min(value = 0, message = "Il prezzo finale deve essere maggiore o uguale a zero")
    private double prezzoFinale;
    private SpettacoloDTO spettacolo;
    private ClienteDTO cliente;
    private double sconto;
	
	public BigliettoDTO(Long id, @Min(value = 0, message = "Il prezzo finale deve essere maggiore o uguale a zero") double prezzoFinale,
			SpettacoloDTO spettacolo, ClienteDTO cliente) 
	{
		super();
		this.id = id;
		this.prezzoFinale = prezzoFinale;
		this.spettacolo = spettacolo;
		this.cliente = cliente;
	}
}