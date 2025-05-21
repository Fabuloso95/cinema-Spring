package org.elis.dto;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportVenditeDTO
{
    private double totaleRicavo;
    private int totaleBigliettiVenduti;
    private List<VenditePerFilmDTO> venditePerFilm;
    private List<VenditePerPeriodoDTO> venditePerPeriodo;
}