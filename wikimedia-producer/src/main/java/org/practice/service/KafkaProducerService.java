package org.practice.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Service
public class KafkaProducerService {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.producer.key.serializer}")
    private String keySerializer;

    @Value("${kafka.producer.value.serializer}")
    private String valueSerializer;

    @Value("${kafka.producer.idempotence}")
    private String producerIdempotence;

    @Value("${kafka.producer.ackConfig}")
    private String producerAckConfig;

    @Value("${kafka.producer.retries}")
    private String retries;

    @Value("${kafka.producer.lingerMsConfig}")
    private String lingerMsConfig;

    @Value("${kafka.producer.batchSize}")
    private String batchSize;

    @Value("${kafka.producer.compressionType}")
    private String compressionType;

    public void sendMessage(String topic, String value) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, producerIdempotence);
        properties.put(ProducerConfig.ACKS_CONFIG, producerAckConfig);
        properties.put(ProducerConfig.RETRIES_CONFIG, retries);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, lingerMsConfig);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);

        Producer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,value);


        producer.send(record, (metadata, exception) -> {
            if(exception!=null){
                log.error("Error while producing message: " + exception.getMessage());
            }else {
                log.info("Message sent successfully: " + metadata.toString());
            }
        });

    }

}
