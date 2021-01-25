package com.pe.document;

import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "student")
public class Student {
    
    private String id;
    
    @NotEmpty
    @Size(min = 3, message = "Tamaño no permitido")
    private String firstName;
    
    //@NotEmpty
    //@Size(min = 3, message = "Tamaño no permitido")
    private String lastName;
    
    //@NotEmpty
    //@Size(min = 8, message = "Tamaño no permitido")
    private String dni;
    
    private String edad;
    
    private LocalTime time;

}
