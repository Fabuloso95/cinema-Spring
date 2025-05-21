package org.elis.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistaDTO 
{
    private Long id;
    @NotBlank(message = "Il nome del regista è obbligatorio")
    private String nome;
     @NotBlank(message = "Il cognome del regista è obbligatorio")
    private String cognome;
}