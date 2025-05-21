package org.elis.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfiloUtenteDTO
{
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotNull
    private LocalDate dataNascita;
}