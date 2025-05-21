package org.elis.dto;

import java.time.LocalDate;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UtenteDTO
{
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "Lo username è obbligatorio")
    private String username;

    @NotNull(message = "La data di nascita è obbligatoria")
    private LocalDate dataNascita;

    @NotBlank(message = "Il ruolo è obbligatorio")
    private String ruolo;

    private String password;

    public UtenteDTO(Long id, String nome, String cognome, String username, LocalDate dataNascita, String ruolo, String password)
    {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.dataNascita = dataNascita;
        this.ruolo = ruolo;
        this.password = password;
    }
}