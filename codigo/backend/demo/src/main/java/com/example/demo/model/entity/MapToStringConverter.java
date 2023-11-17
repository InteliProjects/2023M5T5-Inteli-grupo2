package com.example.demo.model.entity;

import java.io.IOException;
import java.util.Map;

import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.data.neo4j.core.convert.Neo4jPersistentPropertyConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * A custom Neo4j Persistent Property Converter for converting between a Map of String and Object
 * and its JSON string representation when persisting data to the Neo4j database.
 */
public class MapToStringConverter implements Neo4jPersistentPropertyConverter<Map<String, Object>> {
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts a Map of String and Object to its JSON string representation.
     *
     * @param value The Map to be converted.
     * @return A Neo4j Value representing the JSON string.
     * @throws RuntimeException if there's an error during JSON serialization.
     */
    @Override
    public Value write(Map<String, Object> value) {
        try {
            String json = objectMapper.writeValueAsString(value);
            return Values.value(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a JSON string to a Map of String and Object.
     *
     * @param value The Neo4j Value representing the JSON string.
     * @return The deserialized Map.
     * @throws RuntimeException if there's an error during JSON deserialization.
     */
    @Override
    public Map<String, Object> read(Value value) {
        try {
            String json = value.asString();
            return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
}
