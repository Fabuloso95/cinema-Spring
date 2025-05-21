package org.elis.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteDTO extends UtenteDTO
{
    private TesseraFedeltaDTO tesseraFedelta;
}