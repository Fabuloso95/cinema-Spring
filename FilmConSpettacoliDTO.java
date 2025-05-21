package org.elis.dto;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmConSpettacoliDTO
{
    private FilmDTO film;
    private List<SpettacoloDTO> spettacoli;
}