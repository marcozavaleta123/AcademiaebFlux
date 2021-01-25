package com.pe.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.document.Course;
import com.pe.service.CourseService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    
    @GetMapping
    public Mono<ResponseEntity<Flux<Course>>> list() {
	Flux<Course> fxCourses = courseService.findAll();

	return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fxCourses));
    }
    
    @PostMapping
    public Mono<ResponseEntity<Course>> save(@Valid @RequestBody Course course) {
	return courseService.save(course)
		.map(p -> ResponseEntity.accepted().contentType(MediaType.APPLICATION_JSON).body(p));
    }
    
    @PutMapping
    public Mono<ResponseEntity<String>> update(@Valid @RequestBody Course course){
	return courseService.update(course);
    }
    
}
