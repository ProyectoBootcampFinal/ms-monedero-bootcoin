package com.everis.ms.monedero.bootcoin.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.everis.ms.monedero.bootcoin.entity.Wallet;
import com.everis.ms.monedero.bootcoin.utils.WalletYankiUtils;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {


    @Bean
    public ConsumerFactory<String, Wallet> noCustomerConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        JsonDeserializer<Wallet> deserializer = new JsonDeserializer<Wallet>(Wallet.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, WalletYankiUtils.SERVER_CONFIG);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, WalletYankiUtils.CONSUMER_GROUP);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
        		deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Wallet> walletKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Wallet> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(noCustomerConsumerFactory());
        return factory;
    }
    
    
    @Bean
    public ProducerFactory<String, Wallet> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, WalletYankiUtils.SERVER_CONFIG);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, Wallet> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    

}
