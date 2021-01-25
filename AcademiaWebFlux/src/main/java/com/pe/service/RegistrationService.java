package com.pe.service;

import com.pe.document.Registration;
import com.pe.dto.RegistrationDTO;

import reactor.core.publisher.Mono;

public interface RegistrationService {

    Mono<RegistrationDTO> findById(String id);
    
    Mono<Registration> findByStudentId(String studentId);
    
    Mono<Registration> save(Registration registration);
    
}
