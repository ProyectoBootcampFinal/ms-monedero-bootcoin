package com.everis.ms.monedero.bootcoin.kafka.producer;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import com.everis.ms.monedero.bootcoin.entity.Wallet;



public interface IKafkaProducer{
	
	ListenableFuture<SendResult<String, Wallet>> sendJson(String topic, Wallet noCustomer);	
}
