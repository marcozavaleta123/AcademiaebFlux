package com.pe.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.pe.document.Usuario;

import reactor.core.publisher.Mono;

public interface IUsuarioRepo extends ReactiveMongoRepository<Usuario, String>{

	Mono<Usuario> findOneByUsuario(String usuario);
}
