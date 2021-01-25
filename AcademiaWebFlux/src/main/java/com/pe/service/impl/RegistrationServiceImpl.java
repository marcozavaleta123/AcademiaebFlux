package com.pe.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pe.document.Registration;
import com.pe.dto.RegistrationDTO;
import com.pe.repository.CourseRepository;
import com.pe.repository.RegistrationRepository;
import com.pe.repository.StudentRepository;
import com.pe.service.RegistrationService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    	
    @Override
    public Mono<RegistrationDTO> findById(String id) {
	Mono<Registration> registration = registrationRepository.findById(id);
	
	return registration
		.flatMap(r -> {
	            return Mono.just(RegistrationDTO.builder()
		               .id(r.getId())
		               .state(r.getState())
		               .date(r.getDate())
		               .build())
		         .zipWith(studentRepository.findById(r.getStudentId()), (reg, s) -> { 
		             reg.setStudent(s);
		             return reg;
		          })
		         .flatMap(reg -> {
		             return Flux.fromIterable(r.getCourseIdList()).flatMap(c -> 
		        	 courseRepository.findById(c)
		             ).collectList().flatMap(list -> {	
					reg.setCourseList(list);
					return Mono.just(reg);
					});
		         });
	        });
	 
    }

    @Override
    public Mono<Registration> save(Registration registration) {
	registration.setDate(LocalDateTime.now());
	
	return registrationRepository.save(registration);
    }

    @Override
    public Mono<Registration> findByStudentId(String studentId) {
	return registrationRepository.findByStudentId(studentId);
    }
    
}
