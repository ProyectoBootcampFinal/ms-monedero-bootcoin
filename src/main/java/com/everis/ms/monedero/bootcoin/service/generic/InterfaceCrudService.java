package com.everis.ms.monedero.bootcoin.service.generic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//T = Any Object

public interface InterfaceCrudService<T> {

	Mono<T> create(Mono<T> wallet);
	
	Flux<T> findAll();
	
	Mono<T> findById(String id);
	
	Mono<T> update(Mono<T> wallet);
	
	Mono<Void> delete(String id);
	
}
