package org.elis.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AttoreDTO 
{
    private Long id;
    @NotBlank(message = "Il nome dell'attore è obbligatorio")
    private String nome;
    @NotBlank(message = "Il cognome dell'attore è obbligatorio")
    private String cognome;
	
	public AttoreDTO(Long id, @NotBlank(message = "Il nome dell'attore è obbligatorio") String nome,
			@NotBlank(message = "Il cognome dell'attore è obbligatorio") String cognome) 
	{
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
	}
}