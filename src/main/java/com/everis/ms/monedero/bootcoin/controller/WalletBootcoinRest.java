package com.everis.ms.monedero.bootcoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.ms.monedero.bootcoin.entity.Wallet;
import com.everis.ms.monedero.bootcoin.service.WalletService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/walletbootcoin")
public class WalletBootcoinRest {
	
	@Autowired
	private WalletService walletService;
	
	@GetMapping
	public Flux<Wallet> findAll(){
		log.info("WalletBootcoinRest.findAll, calling");
		return walletService.findAll();
	}
	
	@GetMapping("/findById")
	public Mono<Wallet> findById(@RequestParam String id){
		log.info("WalletBootcoinRest.findById, id value has -> "+id);
		return walletService.findById(id);
	}
	
	@GetMapping("/findByIdentityNumber")
	public Mono<Wallet> findByIdentityNumber(@RequestParam String identityNumber){
		log.info("WalletBootcoinRest.findByIdentityNumber, id value has -> "+identityNumber);
		//return walletService.findByIdentityNumber(identityNumber);
		return null;
	}	
	
	@PostMapping
	public Mono<Wallet> insert(@RequestBody Mono<Wallet> noCustomer){
		log.info("WalletBootcoinRest.insert is calling");
		return walletService.create(noCustomer);
	}  
	
	@PutMapping
	public Mono<Wallet> update(@RequestBody Mono<Wallet> noCustomer){
		log.info("WalletBootcoinRest.update is calling");
		return walletService.update(noCustomer);
	}
	
	@DeleteMapping
	public Mono<Void> delete(@RequestParam String id){
		log.info("WalletBootcoinRest.delete is calling");
		return walletService.delete(id);
	}
	
}
