package org.practice.controller;


import lombok.extern.slf4j.Slf4j;
import org.practice.service.OpenSearchConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OpenSearchController {

    private final OpenSearchConsumerService openSearchConsumerService;

    public OpenSearchController(OpenSearchConsumerService openSearchConsumerService) {
        this.openSearchConsumerService = openSearchConsumerService;
    }

    @GetMapping(value = "/get-data", produces="text/event-stream")
    public void getWikimediaData(){
        openSearchConsumerService.consumeData();
    }
}
