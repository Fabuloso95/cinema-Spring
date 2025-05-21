package org.elis.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SalaDTO 
{
    private Long id;
    @NotBlank(message = "Il nome della sala è obbligatorio")
    private String nome;
    @Min(value = 1, message = "La capienza deve essere maggiore di zero")
    private int capienza;
    private boolean isUsufruibile;
	
	public SalaDTO(Long id, @NotBlank(message = "Il nome della sala è obbligatorio") String nome,
			@Min(value = 1, message = "La capienza deve essere maggiore di zero") int capienza, boolean isUsufruibile) 
    {
		super();
		this.id = id;
		this.nome = nome;
		this.capienza = capienza;
		this.isUsufruibile = isUsufruibile;
	}
}