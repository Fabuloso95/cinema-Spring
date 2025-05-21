package org.elis.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RichiestaRegistrazioneDTO {
    @NotBlank(message = "Lo username è obbligatorio")
    private String username;
    @NotBlank(message = "La password è obbligatoria")
    private String password;
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;
    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;
    @Past(message = "La data di nascita deve essere nel passato")
    private LocalDate dataNascita;
    @NotNull(message = "Il ruolo è obbligatorio")
    private RuoloDTO ruolo;
	
	public RichiestaRegistrazioneDTO(@NotBlank(message = "Lo username è obbligatorio") String username,
			@NotBlank(message = "La password è obbligatoria") String password,
			@NotBlank(message = "Il nome è obbligatorio") String nome,
			@NotBlank(message = "Il cognome è obbligatorio") String cognome,
			@Past(message = "La data di nascita deve essere nel passato") LocalDate dataNascita,
			@NotNull(message = "Il ruolo è obbligatorio") RuoloDTO ruolo) 
    {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.ruolo = ruolo;
	}
}