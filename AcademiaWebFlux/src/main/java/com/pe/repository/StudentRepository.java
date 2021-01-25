package com.pe.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.pe.document.Student;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

}
