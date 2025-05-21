package org.elis.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneMenuRequestDTO
{
    @NotNull
    private Long idUtente;
    @NotNull
    private Long idProdottoMenu;
    @Min(value = 1)
    private int quantita;
}