package com.pe.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pe.document.Course;
import com.pe.service.CourseService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseHandler {

    private final CourseService courseService;
    
    public Mono<ServerResponse> list(ServerRequest req) {
	return ServerResponse
		.ok()
		.contentType(MediaType.APPLICATION_JSON)
		.body(courseService.findAll(), Course.class);
    }
    
    public Mono<ServerResponse> save(ServerRequest req) {
	Mono<Course> courseMono = req.bodyToMono(Course.class);

	return courseMono.flatMap(courseService::save)
		.flatMap(c -> ServerResponse.accepted()
			.contentType(MediaType.APPLICATION_JSON).body(fromValue(c)));
    }
    
    public Mono<ServerResponse> update(ServerRequest req) {
	Mono<Course> courseMono = req.bodyToMono(Course.class);

	return courseMono.flatMap(courseService::update)
		.flatMap(c -> ServerResponse.accepted()
			.contentType(MediaType.APPLICATION_JSON).body(fromValue(c)));
    }
    
}
