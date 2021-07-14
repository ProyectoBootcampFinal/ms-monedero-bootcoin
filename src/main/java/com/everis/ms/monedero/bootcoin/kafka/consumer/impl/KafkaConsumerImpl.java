package com.everis.ms.monedero.bootcoin.kafka.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.everis.ms.monedero.bootcoin.entity.Account;
import com.everis.ms.monedero.bootcoin.entity.Wallet;
import com.everis.ms.monedero.bootcoin.kafka.consumer.IKafkaConsumer;
import com.everis.ms.monedero.bootcoin.kafka.producer.IKafkaProducer;
import com.everis.ms.monedero.bootcoin.service.WalletService;
import com.everis.ms.monedero.bootcoin.utils.WalletYankiUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class KafkaConsumerImpl implements IKafkaConsumer{

	@Autowired
	private WalletService walletService;

    @KafkaListener(topics = WalletYankiUtils.CONSUMER_TOPIC, groupId = WalletYankiUtils.CONSUMER_GROUP,
            containerFactory = WalletYankiUtils.CONTAINER_FACTORY)
    public void consumeJson(Wallet wallet) {
        System.out.println("Consumed JSON Message: " + wallet);
        walletService.createWithoutTD(new Wallet(wallet.getPhoneNumber(), wallet.getIdentityNumber(), 0.0))
        .subscribe(e->log.info("Value has ->"+e.toString()));
    }
}
