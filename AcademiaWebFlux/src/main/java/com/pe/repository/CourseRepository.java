package com.pe.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.pe.document.Course;

@Repository
public interface CourseRepository extends ReactiveMongoRepository<Course, String> {

}
