package com.pe.service;

import com.pe.security.User;

import reactor.core.publisher.Mono;

public interface IUsuarioService {
	
	Mono<User> buscarPorUsuario(String usuario);
}
