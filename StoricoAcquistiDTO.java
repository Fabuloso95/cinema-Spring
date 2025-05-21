package org.elis.dto;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoricoAcquistiDTO
{
    private Long idCliente;
    private String usernameCliente;
    private List<BigliettoDTO> bigliettiAcquistati;
    private int numeroTotaleBiglietti;
    private double totaleSpeso;
}