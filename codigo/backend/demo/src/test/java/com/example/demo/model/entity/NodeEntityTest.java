package com.example.demo.model.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeEntityTest {

    private NodeEntity nodeEntity;

    @BeforeEach
    public void setUp() {
        nodeEntity = new NodeEntity();
    }

    @Test
    public void testId() {
        nodeEntity.setId(1L);
        assertEquals(1L, nodeEntity.getId());
    }

    @Test
    public void testName() {
        nodeEntity.setName("TestName");
        assertEquals("TestName", nodeEntity.getName());
    }

    @Test
    public void testType() {
        nodeEntity.setType("TestType");
        assertEquals("TestType", nodeEntity.getType());
    }

    @Test
    public void testRpn() {
        nodeEntity.setRpn("TestRPN");
        assertEquals("TestRPN", nodeEntity.getRpn());
    }

    @Test
    public void testProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("key1", "value1");
        properties.put("key2", 123);
        nodeEntity.setProperties(properties);
        assertEquals(properties, nodeEntity.getProperties());
    }

    @Test
    public void testConnections() {
        EdgeEntity edge1 = new EdgeEntity();
        EdgeEntity edge2 = new EdgeEntity();
        nodeEntity.setConnections(List.of(edge1, edge2));
        assertEquals(2, nodeEntity.getConnections().size());
    }

    @Test
    public void testDistance() {
        nodeEntity.setDistance(5.0);
        assertEquals(5.0, nodeEntity.getDistance(), 0.001); // Use delta for floating-point comparisons
    }

    @Test
    public void testParent() {
        NodeEntity parent = new NodeEntity();
        parent.setName("ParentNode");
        nodeEntity.setParent(parent);
        assertEquals("ParentNode", nodeEntity.getParent().getName());
    }
}
