package com.pe.service;

import org.springframework.http.ResponseEntity;

import com.pe.document.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    
    Flux<Student> findAll();
    
    Mono<Student> findById(String id);

    Mono<Student> save(Student student);
    
    Mono<ResponseEntity<String>> update(Student student);
    
    Mono<ResponseEntity<String>> deleteById(String id);
    
}
