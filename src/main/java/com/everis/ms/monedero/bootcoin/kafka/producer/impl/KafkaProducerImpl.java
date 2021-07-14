package com.everis.ms.monedero.bootcoin.kafka.producer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.everis.ms.monedero.bootcoin.entity.Wallet;
import com.everis.ms.monedero.bootcoin.kafka.producer.IKafkaProducer;


@Service
public class KafkaProducerImpl implements IKafkaProducer{

    @Autowired
    private KafkaTemplate<String, Wallet> kafkaTemplate;
	
	@Override
	public ListenableFuture<SendResult<String, Wallet>> sendJson(String topic, Wallet noCustomer) {
		return kafkaTemplate.send(topic, noCustomer);
	}


}
