package org.example.utilities;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JsonService {
    private final ObjectMapper objectMapper;

    public <T> Optional<T> deserialize(String input, Class<T> cls) {
        try {
            return Optional.of(objectMapper.readValue(input, cls));
        } catch (JsonProcessingException e) {
            log.error("cannot deserialize object {}", input, e);
        }
        return Optional.empty();
    }

    public <T> Optional<String> serialize(T input) {
        try {
            return Optional.of(objectMapper.writeValueAsString(input));
        } catch (JsonProcessingException e) {
            log.error("cannot serialize object {}", input, e);
        }
        return Optional.empty();
    }
}
