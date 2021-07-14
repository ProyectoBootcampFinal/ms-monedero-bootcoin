package com.everis.ms.monedero.bootcoin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.ms.monedero.bootcoin.entity.Wallet;
import com.everis.ms.monedero.bootcoin.exception.NotDataFoundException;
import com.everis.ms.monedero.bootcoin.repository.WalletRepository;
import com.everis.ms.monedero.bootcoin.service.WalletService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class WalletServiceImpl implements WalletService{

	@Autowired
	private WalletRepository walletRepository;

	@Override
	public Mono<Wallet> create(Mono<Wallet> wallet) {
		log.info("WalletServiceImpl.create is calling");
		return wallet.flatMap(walletRepository::save);
	}

	@Override
	public Flux<Wallet> findAll() {
		log.info("WalletServiceImpl.findAll is calling");
		return walletRepository.findAll();
	}

	@Override
	public Mono<Wallet> findById(String id) {
		log.info("WalletServiceImpl.findById has ID value -> "+id);
		return walletRepository.findById(id);
	}

	@Override
	public Mono<Wallet> update(Mono<Wallet> customer) {
		log.info("WalletServiceImpl.update is calling");
		return customer.flatMap(
				p->walletRepository.findById(p.getId())
				.switchIfEmpty(Mono.error(new NotDataFoundException("No existe billetera")))
				.then(walletRepository.save(p))
				.doOnError(e->{
					log.error("NoCustomerServiceImpl.update has error -> "+e);
					new Exception(e);
				})
				);
	}

	@Override
	public Mono<Void> delete(String id) {
		return walletRepository.deleteById(id);
	}

	@Override
	public Mono<Wallet> createWithoutTD(Wallet wallet) {
		log.info("WalletServiceImpl.createWithoutTD is calling");
		return walletRepository.save(wallet);
	}

}
