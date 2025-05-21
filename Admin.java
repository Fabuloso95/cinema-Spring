package org.elis.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "utente_id")
@NoArgsConstructor
@Getter @Setter
@ToString
public class Admin extends Utente
{
     public Admin(String nome, String cognome, String username, String password, Ruolo ruolo, LocalDate dataNascita)
     {
         super(nome, cognome, username, password, ruolo, dataNascita);
     }
}