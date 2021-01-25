package com.pe.document;

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
@Document(collection = "course")
public class Course {

    private String id;
    
    //@NotEmpty
    //@Size(min = 3, message = "Tamaño no permitido")
    private String name;
    
    private String acronym;
    private Boolean state;
    
}
