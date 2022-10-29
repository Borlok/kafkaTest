package com.example.kafkatest.controller;

import com.example.kafkatest.model.StarshipDto;
import com.example.kafkatest.service.StarshipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class KafkaControllerV1 {
    private StarshipService service;

    public KafkaControllerV1(StarshipService service) {
        this.service = service;
    }

    @GetMapping
    public String sendMessage() {
        service.send(new StarshipDto("Starkiller"));
        return "Hello";
    }
}
