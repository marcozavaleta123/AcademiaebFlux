package com.pe.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pe.document.Registration;
import com.pe.service.RegistrationService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RegistrationHandler {

    private final RegistrationService registrationService;
    
    public Mono<ServerResponse> findById(ServerRequest req){
	String id = req.pathVariable("id");
	return registrationService.findById(id)
		.flatMap(c -> ServerResponse
			.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(fromValue(c))
	);				
    }
    
    public Mono<ServerResponse> save(ServerRequest req) {
	Mono<Registration> registerMono = req.bodyToMono(Registration.class);

	return registerMono.flatMap(registrationService::save)
		.flatMap(c -> ServerResponse.accepted()
			.contentType(MediaType.APPLICATION_JSON).body(fromValue(c)));
    }
    
}
