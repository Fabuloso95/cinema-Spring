package org.elis.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
public class AdminDTO extends UtenteDTO
{
	private String dipartimento;
    private List<String> privilegi;
    
    public AdminDTO(Long id, String nome, String cognome, String username, LocalDate dataNascita,
			String ruolo, String password) 
    {
		super(id, nome, cognome, username, dataNascita, ruolo, password);
	}
    
	public AdminDTO(Long id, String nome, String cognome, String username, String email, LocalDate dataNascita,
			String ruolo, String dipartimento, List<String> privilegi, String password) 
	{
		super(id, nome, cognome, username, dataNascita, ruolo, password);
		this.dipartimento = dipartimento;
		this.privilegi = privilegi;
	}
}