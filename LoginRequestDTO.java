package org.elis.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequestDTO implements Serializable 
{
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Lo username è obbligatorio")
    private String username;

    @NotBlank(message = "La password è obbligatoria")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;
    
	public LoginRequestDTO(@NotBlank(message = "Lo username è obbligatorio") String username,
			@NotBlank(message = "La password è obbligatoria") String password) 
	{
		super();
		this.username = username;
		this.password = password;
	}
}