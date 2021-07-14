package com.everis.ms.monedero.bootcoin.kafka.consumer;

import com.everis.ms.monedero.bootcoin.entity.Wallet;

public interface IKafkaConsumer {

    public void consumeJson(Wallet noCustomer);
}
