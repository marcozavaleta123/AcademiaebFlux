package com.pe.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.document.Registration;
import com.pe.dto.RegistrationDTO;
import com.pe.service.RegistrationService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;
    
    @GetMapping("/{id}")
    public Mono<ResponseEntity<RegistrationDTO>> findById(@PathVariable String id) {
	return registrationService.findById(id)
		.map(p -> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(p)
		).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Mono<ResponseEntity<Registration>> save(@Valid @RequestBody Registration registration) {
	return registrationService.save(registration)
		.map(r -> ResponseEntity.accepted().contentType(MediaType.APPLICATION_JSON).body(r));
    }
}
