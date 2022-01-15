package org.example.utilities;

import java.util.Map;
import java.util.Optional;

import org.example.json.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonServiceTest extends BaseTest {
    @Test
    void serialize() {
        JsonService jsonService = getBean(JsonService.class);
        Map<String, String> input = Map.of("foo", "bar");
        String expected = "{\"foo\":\"bar\"}";
        Optional<String> actual = jsonService.serialize(input);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());

        Optional<Map> actualMap = jsonService.deserialize(expected, Map.class);
        assertTrue(actualMap.isPresent());
        assertEquals(input, actualMap.get());
    }
}
