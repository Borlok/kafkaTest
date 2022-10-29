package com.example.kafkatest.service;

import com.example.kafkatest.model.StarshipDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StarshipServiceImpl implements StarshipService{

    private final KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    public StarshipServiceImpl(KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate) {
        this.kafkaStarshipTemplate = kafkaStarshipTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void send(StarshipDto dto) {
        kafkaStarshipTemplate.send("server.starship", dto);
    }

    @Override
    @KafkaListener(id = "starship", topics = {"server.starship"}, containerFactory = "singleFactory")
    public void consume(StarshipDto dto) {
        System.out.println("Incoming value: " + writeValueAsString(dto));
    }

    private String writeValueAsString(StarshipDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
