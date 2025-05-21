package org.elis.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SpettacoloCreationDTO 
{
    @NotNull(message = "La data e l'ora dello spettacolo sono obbligatorie")
    @Future(message = "La data e l'ora dello spettacolo devono essere nel futuro")
    private LocalDateTime dataOra;

    @NotNull(message = "Il prezzo base del biglietto è obbligatorio")
    @Min(value = 0, message = "Il prezzo base deve essere maggiore o uguale a zero")
    private Double prezzoBaseBiglietto;

    @NotNull(message = "L'ID della sala è obbligatorio")
    private Long salaId;

    @NotNull(message = "L'ID del film è obbligatorio")
    private Long filmId;

	public SpettacoloCreationDTO(
			@NotNull(message = "La data e l'ora dello spettacolo sono obbligatorie") @Future(message = "La data e l'ora dello spettacolo devono essere nel futuro") LocalDateTime dataOra,
			@NotNull(message = "Il prezzo base del biglietto è obbligatorio") @Min(value = 0, message = "Il prezzo base deve essere maggiore o uguale a zero") Double prezzoBaseBiglietto,
			@NotNull(message = "L'ID della sala è obbligatorio") Long salaId,
			@NotNull(message = "L'ID del film è obbligatorio") Long filmId) {
		super();
		this.dataOra = dataOra;
		this.prezzoBaseBiglietto = prezzoBaseBiglietto;
		this.salaId = salaId;
		this.filmId = filmId;
	}
}