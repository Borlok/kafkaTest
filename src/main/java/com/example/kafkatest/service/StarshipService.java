package com.example.kafkatest.service;

import com.example.kafkatest.model.StarshipDto;

public interface StarshipService {
    void send(StarshipDto dto);
    void consume(StarshipDto dto);
}
