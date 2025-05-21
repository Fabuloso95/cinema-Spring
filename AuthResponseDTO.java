package org.elis.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AuthResponseDTO implements Serializable 
{
    private static final long serialVersionUID = 1L;

    private String token;
    private String username;
    private String ruolo;
}