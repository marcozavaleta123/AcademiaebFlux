package com.pe.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.document.Student;
import com.pe.service.StudentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    
    private final StudentService studentService;

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<ResponseEntity<Flux<Student>>> list() {
	Flux<Student> fxStudents = studentService.findAll();

	return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(fxStudents));
    }
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Student>> findById(@PathVariable("id") String id){
	return studentService.findById(id) //Mono<Plato> | Mono.empty
		.map(p -> ResponseEntity.ok()
			   .contentType(MediaType.APPLICATION_JSON)
			   .body(p)
		     ).defaultIfEmpty(ResponseEntity.notFound().build());
				
    }
    
    @PostMapping
    public Mono<ResponseEntity<Student>> save(@Valid @RequestBody Student student) {
	return studentService.save(student)
		.map(p -> ResponseEntity.accepted().contentType(MediaType.APPLICATION_JSON).body(p));
    }
    
    @PutMapping
    public Mono<ResponseEntity<String>> update(@Valid @RequestBody Student student){
	return studentService.update(student);
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable("id") String id){
	/**return studentService.findById(id)
		.flatMap(p -> {
				return studentService.deleteById(p.getId())
					.then(Mono.just(ResponseEntity.status(HttpStatus.ACCEPTED).body("El estudiante seleccionado se eliminó correctamente")));
	})
	.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID del estudiante ingresado no existe"));
	**/
	
	return studentService.deleteById(id);
    }

}
