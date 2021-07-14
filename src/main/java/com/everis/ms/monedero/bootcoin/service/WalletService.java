package com.everis.ms.monedero.bootcoin.service;

import com.everis.ms.monedero.bootcoin.entity.Wallet;
import com.everis.ms.monedero.bootcoin.service.generic.InterfaceCrudService;

import reactor.core.publisher.Mono;

/**
 * Interface del Service con metodos externos al crud.
 */
public interface WalletService extends InterfaceCrudService<Wallet>{
	public Mono<Wallet> createWithoutTD(Wallet wallet);
}
