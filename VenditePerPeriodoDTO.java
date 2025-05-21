package org.elis.dto;

import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenditePerPeriodoDTO
{
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int totaleBigliettiVenduti;
    private double totaleRicavo;
}