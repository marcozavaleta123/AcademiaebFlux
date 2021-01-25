package com.pe.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.pe.document.Course;
import com.pe.document.Student;

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
public class RegistrationDTO {

    private String id;
    private LocalDateTime date;
    private Student student;
    private List<Course> courseList;
    private Boolean state;
    
}
