package org.elis.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SpettacoloDTO 
{
    private Long id;
    @Future(message = "La data e l'ora dello spettacolo devono essere nel futuro")
    private LocalDateTime dataOra;
    private FilmDTO film;
    private SalaDTO sala;
    @Min(value = 0, message = "Il prezzo base deve essere maggiore o uguale a zero")
    private double prezzoBaseBiglietto;
	
	public SpettacoloDTO(Long id,
			@Future(message = "La data e l'ora dello spettacolo devono essere nel futuro") LocalDateTime dataOra,
			FilmDTO film, SalaDTO sala,
			@Min(value = 0, message = "Il prezzo base deve essere maggiore o uguale a zero") double prezzoBaseBiglietto) 
    {
		super();
		this.id = id;
		this.dataOra = dataOra;
		this.film = film;
		this.sala = sala;
		this.prezzoBaseBiglietto = prezzoBaseBiglietto;
	}
}