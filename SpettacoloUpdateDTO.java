package org.elis.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SpettacoloUpdateDTO 
{
    @Future(message = "La data e l'ora dello spettacolo devono essere nel futuro")
    private LocalDateTime dataOra;

    @Min(value = 0, message = "Il prezzo base deve essere maggiore o uguale a zero")
    private double prezzoBaseBiglietto;

    private Long salaId;
    private Long filmId;
	
    public SpettacoloUpdateDTO(
			@Future(message = "La data e l'ora dello spettacolo devono essere nel futuro") LocalDateTime dataOra,
			@Min(value = 0, message = "Il prezzo base deve essere maggiore o uguale a zero") double prezzoBaseBiglietto,
			Long salaId, Long filmId) 
    {
		super();
		this.dataOra = dataOra;
		this.prezzoBaseBiglietto = prezzoBaseBiglietto;
		this.salaId = salaId;
		this.filmId = filmId;
	}
}