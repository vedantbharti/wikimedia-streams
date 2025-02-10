package org.practice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class WikimediaEventService {

    private final WebClient.Builder webClientBuilder;

    private final KafkaProducerService kafkaProducerService;

    public WikimediaEventService(WebClient.Builder webClientBuilder, KafkaProducerService kafkaProducerService){
        this.webClientBuilder = webClientBuilder;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Flux<String> getWikimediaData() {
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        return webClientBuilder.baseUrl(url)
                .build()
                .get()
                .retrieve()
                .bodyToFlux(String.class)
                .doOnNext(item -> {
                    kafkaProducerService.sendMessage("wikimedia.recentchange", item);
                    System.out.println(item);
                });
    }
}
