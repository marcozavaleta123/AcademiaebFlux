package com.pe.document;

import java.time.LocalDateTime;
import java.util.List;

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
@Document(collection = "registration")
public class Registration {
    
    private String id;
    private LocalDateTime date;
    private String studentId;
    private List<String> courseIdList;
    private Boolean state;

}
