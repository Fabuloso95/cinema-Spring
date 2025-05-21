package org.elis.exceptionhandler;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessaggioErrore 
{
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
    private Map<String, String> messaggi;
    
    public MessaggioErrore(int status, String error, String path, Map<String, String> messaggi) 
    {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.path = path;
        this.messaggi = messaggi;
    }
}