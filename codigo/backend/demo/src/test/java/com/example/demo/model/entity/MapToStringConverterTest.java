package com.example.demo.model.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MapToStringConverterTest {

    private MapToStringConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new MapToStringConverter();
    }

    @Test
    public void testWrite() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", 42);

        Value result = converter.write(data);

        assertEquals(Values.value("{\"key1\":\"value1\",\"key2\":42}"), result);
    }

    @Test
    public void testRead() {
        String json = "{\"key1\":\"value1\",\"key2\":42}";
        Value neo4jValue = Values.value(json);

        Map<String, Object> result = converter.read(neo4jValue);

        assertEquals(2, result.size());
        assertEquals("value1", result.get("key1"));
        assertEquals(42, result.get("key2"));
    }

    @Test
public void testWriteWithSerializationError() throws JsonProcessingException {
    ObjectMapper objectMapper = mock(ObjectMapper.class);
    when(objectMapper.writeValueAsString(new HashMap<>())).thenThrow(new JsonProcessingException("Erro de serialização") {
    });

    converter = new MapToStringConverter();
    converter.objectMapper = objectMapper;

    Map<String, Object> data = new HashMap<>();

    assertThrows(RuntimeException.class, () -> converter.write(data));
}

    @Test
    public void testReadWithDeserializationError() {
        Value neo4jValue = Values.value("json invalido");

        assertThrows(RuntimeException.class, () -> converter.read(neo4jValue));
    }
}

