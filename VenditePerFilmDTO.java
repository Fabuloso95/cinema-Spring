package org.elis.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenditePerFilmDTO
{
    private Long filmId;
    private String titoloFilm;
    private int totaleBigliettiVenduti;
    private double totaleRicavo;
}