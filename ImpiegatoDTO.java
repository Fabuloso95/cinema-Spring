package org.elis.dto;

import java.time.LocalDate;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImpiegatoDTO extends UtenteDTO
{
	private String matricola;
    private String ufficio;
	
    public ImpiegatoDTO(Long id, String nome, String cognome, String username, LocalDate dataNascita,
			String ruolo, String password)
    {
		super(id, nome, cognome, username, dataNascita, ruolo, password);
	}
	public ImpiegatoDTO(Long id, String nome, String cognome, String username, LocalDate dataNascita,
			String ruolo, String password, String matricola, String ufficio) 
	{
		super(id, nome, cognome, username, dataNascita, ruolo, password);
		this.matricola = matricola;
		this.ufficio = ufficio;
	}
}