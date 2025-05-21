package org.elis.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DettaglioSpettacoloDTO
{
    private Long id;
    @Future
    @NotNull
    private LocalDateTime dataOra;
    @NotNull
    private FilmDTO film;
    @NotNull
    private SalaDTO sala;
    @Min(value = 0)
    private double prezzoBaseBiglietto;
    @Min(value = 0)
    private int postiDisponibili;
}