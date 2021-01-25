package com.pe.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.pe.document.Registration;

import reactor.core.publisher.Mono;

@Repository
public interface RegistrationRepository extends ReactiveMongoRepository<Registration, String> {

    Mono<Registration> findByStudentId(String studentId);
    
}
