package com.pe.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pe.document.Course;
import com.pe.repository.CourseRepository;
import com.pe.service.CourseService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    
    @Override
    public Flux<Course> findAll() {
	return courseRepository.findAll();
    }

    @Override
    public Mono<Course> save(Course course) {
	return courseRepository.save(course);
    }

    @Override
    public Mono<ResponseEntity<String>> update(Course course) {
	return courseRepository.findById(course.getId())
		.flatMap(p -> {
				return save(course)
					.then(Mono.just(ResponseEntity.status(HttpStatus.ACCEPTED).body("El curso seleccionado se actualizó correctamente")));
	})
	.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID del curso ingresado no existe"));
    }

}
