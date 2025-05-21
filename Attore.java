package org.elis.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "attori")
@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Attore 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @ManyToMany(mappedBy = "attori", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Film> films = new ArrayList<>();

	public Attore(Long id, String nome, String cognome, List<Film> films)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.films = films;
	}
}