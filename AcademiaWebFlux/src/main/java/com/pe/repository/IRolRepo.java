package com.pe.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.pe.document.Rol;

public interface IRolRepo extends ReactiveMongoRepository<Rol, String>{

}
