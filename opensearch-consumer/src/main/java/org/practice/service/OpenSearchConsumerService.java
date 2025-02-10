package org.practice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Properties;

@Slf4j
@Service
public class OpenSearchConsumerService {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.consumer.group.id}")
    private String groupId;

    @Value("${kafka.consumer.key.deserializer}")
    private String keyDeserializer;

    @Value("${kafka.consumer.value.deserializer}")
    private String valueDeserializer;

    public void consumeData() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("group.id", groupId);
        properties.put("key.deserializer", keyDeserializer);
        properties.put("value.deserializer", valueDeserializer);
        properties.put("auto.offset.reset", "earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("wikimedia.recentchange"));


        while(true){
            ConsumerRecords<String,String> records = consumer.poll(500);
            log.info("polling");
            records.forEach(record -> {
                log.info(record.value());
            });
        }
    }
}
