package org.elis.dto;

import org.elis.model.DettaglioOrdine;
import lombok.*;
import jakarta.validation.constraints.Min;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DettaglioOrdineDTO
{
    private Long id;

    private Long menuId;

    @Min(value = 1, message = "La quantità deve essere almeno 1")
    private int quantita;

    @Min(value = 0, message = "Il prezzo unitario non può essere negativo")
    private double prezzoUnitario;

    public DettaglioOrdineDTO(DettaglioOrdine dettaglioOrdine)
    {
        this.id = dettaglioOrdine.getId();
        if (dettaglioOrdine.getMenu() != null) 
        {
            this.menuId = dettaglioOrdine.getMenu().getId();
        }
        this.quantita = dettaglioOrdine.getQuantita();
        this.prezzoUnitario = dettaglioOrdine.getPrezzoUnitario();
    }
}