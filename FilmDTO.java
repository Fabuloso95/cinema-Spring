package org.elis.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FilmDTO 
{
    private Long id;
    @NotBlank(message = "Il titolo del film è obbligatorio")
    private String titolo;
    @NotBlank(message = "Il genere del film è obbligatorio")
    private String genere;
    @Min(value = 1, message = "La durata deve essere maggiore di zero")
    private int durata;
    @PastOrPresent(message = "L'anno di uscita deve essere nel passato o presente")
    private int annoUscita;
    private List<AttoreDTO> attori;
    private RegistaDTO regista;
	
    public FilmDTO(Long id, @NotBlank(message = "Il titolo del film è obbligatorio") String titolo,
			@NotBlank(message = "Il genere del film è obbligatorio") String genere,
			@Min(value = 1, message = "La durata deve essere maggiore di zero") int durata,
			@PastOrPresent(message = "L'anno di uscita deve essere nel passato o presente") int annoUscita,
			List<AttoreDTO> attori, RegistaDTO regista) 
    {
		super();
		this.id = id;
		this.titolo = titolo;
		this.genere = genere;
		this.durata = durata;
		this.annoUscita = annoUscita;
		this.attori = attori;
		this.regista = regista;
	}
}