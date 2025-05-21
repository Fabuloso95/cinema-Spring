package org.elis.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TesseraFedeltaDTO 
{
    private Long id;
    private String codiceTessera;
    private int punti;
    private LocalDate dataEmissione;
    private Long idCliente;
    private String usernameCliente;
}