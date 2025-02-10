package org.practice;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;


@SpringBootApplication
public class WikimediaProducer {

//    private static final Logger log = LoggerFactory.getLogger(WikimediaProducer.class.getSimpleName());
    public static void main(String[] args) {

        SpringApplication.run(WikimediaProducer.class, args);
    }
}