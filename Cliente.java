package org.elis.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clienti")
@PrimaryKeyJoinColumn(name = "utente_id")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Cliente extends Utente
{
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private TesseraFedelta tesseraFedelta;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Biglietto> bigliettiAcquistati = new ArrayList<>();

    public Cliente(String nome, String cognome, String username, String password, Ruolo ruolo, LocalDate dataNascita)
    {
        super(nome, cognome, username, password, ruolo, dataNascita);
    }
    public Cliente(Long id, String nome, String cognome, String username, LocalDate dataNascita,
            Ruolo ruolo, String password, TesseraFedelta tesseraFedelta, List<Biglietto> bigliettiAcquistati)
    {
        super(id, nome, cognome, username, dataNascita, ruolo, password);
        this.tesseraFedelta = tesseraFedelta;
        this.bigliettiAcquistati = bigliettiAcquistati;
    }
}