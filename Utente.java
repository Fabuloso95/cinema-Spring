package org.elis.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utenti")
@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Utente 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;
    
    @Column(nullable = false, name= "data_nascita")
    private LocalDate dataNascita;

	public Utente(Long id, String nome, String cognome, String username, LocalDate dataNascita, Ruolo ruolo, String password)
	{
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.dataNascita = dataNascita;
		this.ruolo = ruolo;
		this.password = password;
	}

	public Utente(String nome, String cognome, String username, String password, Ruolo ruolo, LocalDate dataNascita) 
	{
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
		this.dataNascita = dataNascita;
	}

	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}
}