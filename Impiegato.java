package org.elis.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "impiegati")
@PrimaryKeyJoinColumn(name = "utente_id")
@Getter @Setter
@NoArgsConstructor
public class Impiegato extends Utente
{
     public Impiegato(String nome, String cognome, String username, String password, Ruolo ruolo, LocalDate dataNascita)
     {
         super(nome, cognome, username, password, ruolo, dataNascita);
     }
}
