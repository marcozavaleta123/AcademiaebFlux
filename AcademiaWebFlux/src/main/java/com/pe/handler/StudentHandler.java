package com.pe.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pe.document.Student;
import com.pe.service.StudentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentHandler {

    private final StudentService studentService;
    
    public Mono<ServerResponse> list(ServerRequest req) {
	return ServerResponse
		.ok()
		.contentType(MediaType.APPLICATION_JSON)
		.body(studentService.findAll(), Student.class);
    }
    
    public Mono<ServerResponse> save(ServerRequest req) {
	Mono<Student> studentMono = req.bodyToMono(Student.class);

	return studentMono.flatMap(studentService::save)
		.flatMap(c -> ServerResponse.accepted()
			.contentType(MediaType.APPLICATION_JSON).body(fromValue(c)));
    }
    
    public Mono<ServerResponse> update(ServerRequest req) {
	Mono<Student> studentMono = req.bodyToMono(Student.class);

	return studentMono.flatMap(studentService::update)
		.flatMap(c -> ServerResponse.accepted()
			.contentType(MediaType.APPLICATION_JSON).body(fromValue(c)));
    }

    public Mono<ServerResponse> delete(ServerRequest req){
	String id = req.pathVariable("id");
	return studentService.deleteById(id)
		.flatMap(c -> ServerResponse
			.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(fromValue(c))
	);
    }

}
