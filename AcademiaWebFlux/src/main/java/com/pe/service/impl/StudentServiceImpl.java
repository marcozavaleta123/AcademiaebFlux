package com.pe.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pe.document.Student;
import com.pe.repository.StudentRepository;
import com.pe.service.RegistrationService;
import com.pe.service.StudentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    
    private final RegistrationService registrationService;
    private final StudentRepository studentRepository;

    @Override
    public Flux<Student> findAll() {
	Sort sort = Sort.by(Sort.Direction.DESC, "edad");
	return studentRepository.findAll(sort);
    }

    @Override
    public Mono<Student> save(Student student) {
	return studentRepository.save(student);
    }

    @Override
    public Mono<Student> findById(String id) {
	return studentRepository.findById(id);
    }

    @Override
    public Mono<ResponseEntity<String>> deleteById(String id) {
	return findById(id).flatMap(p -> {
	    return registrationService.findByStudentId(p.getId()).flatMap(r -> {
		if (r != null) {
		    return Mono.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
			    .body("No se pudo eliminar al estudiante seleccionado ya que se encuentra matriculado"));
		}

		return studentRepository.deleteById(p.getId()).flatMap(d -> {
		    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
			    .body("El estudiante seleccionado se eliminó correctamente"));
		});
	    }).defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}).defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID del estudiante ingresado no existe"));
    }

    @Override
    public Mono<ResponseEntity<String>> update(Student student) {
	return findById(student.getId())
		.flatMap(p -> {
				return save(student)
					.then(Mono.just(ResponseEntity.status(HttpStatus.ACCEPTED).body("El estudiante seleccionado se actualizó correctamente")));
	})
	.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID del estudiante ingresado no existe"));
    }

}
