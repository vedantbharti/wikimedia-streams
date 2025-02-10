package org.practice.controller;


import lombok.extern.slf4j.Slf4j;
import org.practice.service.WikimediaEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
public class WikimediaController {

    private WikimediaEventService wikimediaEventService;

    public WikimediaController(WikimediaEventService wikimediaEventService){
        this.wikimediaEventService = wikimediaEventService;
    }


    @GetMapping(value = "/change-data", produces = "text/event-stream")
    public Flux<String> getWikimediaStreamObject() {
//        Flux<Object> data = wikimediaEventService.getWikimediaData();
//        System.out.println(data);
        return wikimediaEventService.getWikimediaData();
    }
}
