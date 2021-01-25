package com.pe.service;

import org.springframework.http.ResponseEntity;

import com.pe.document.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {

    Flux<Course> findAll();
    
    Mono<Course> save(Course course);
    
    Mono<ResponseEntity<String>> update(Course course);
    
}
