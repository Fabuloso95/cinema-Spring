package org.elis.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProdottoMenuDTO 
{
    private Long id;
    @NotBlank(message = "Il nome del prodotto è obbligatorio")
    private String nome;
    @Min(value = 0, message = "Il prezzo deve essere maggiore o uguale a zero")
    private double prezzo;
    private CategoriaProdottoDTO categoria;
    private String urlImmagine;
    private boolean disponibile;
    @Min(value = 1, message = "Il prezzo finale deve essere maggiore di zero")
    private int quantitaDisponibile;
    private String descrizione;
}