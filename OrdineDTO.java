package org.elis.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.elis.model.Ordine;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrdineDTO 
{
    private Long id;
    private Long utenteId;
    private Date dataOrdine;
    private double importoTotale;
    private List<DettaglioOrdineDTO> dettagliOrdine;
    private String statoOrdine;

    public OrdineDTO(Ordine ordine) 
    {
        this.id = ordine.getId();
        this.utenteId = ordine.getUtente().getId();
        this.dataOrdine = ordine.getDataOrdine();
        this.importoTotale = ordine.getImportoTotale();
        this.dettagliOrdine = ordine.getDettagliOrdine().stream()
                .map(DettaglioOrdineDTO::new)
                .collect(Collectors.toList());
        this.statoOrdine = ordine.getStatoOrdine().name();
    }
}