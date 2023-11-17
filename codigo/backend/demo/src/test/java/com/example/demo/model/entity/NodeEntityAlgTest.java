package com.example.demo.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NodeEntityAlgTest {

    private NodeEntityAlg nodeEntity;

    @BeforeEach
    public void setUp() {
        nodeEntity = new NodeEntityAlg("Node1");
    }

    @Test
    public void testGetAndSetId() {
        assertNull(nodeEntity.getId());

        nodeEntity.setId(1L);
        assertEquals(1L, nodeEntity.getId());
    }

    @Test
    public void testGetAndSetName() {
        assertEquals("Node1", nodeEntity.getName());

        nodeEntity.setName("Node2");
        assertEquals("Node2", nodeEntity.getName());
    }

    @Test
    public void testGetAndSetDistance() {
        assertEquals(Double.POSITIVE_INFINITY, nodeEntity.getDistance());

        nodeEntity.setDistance(10.0);
        assertEquals(10.0, nodeEntity.getDistance());
    }

    @Test
    public void testGetAndSetParent() {
        assertNull(nodeEntity.getParent());

        NodeEntityAlg parent = new NodeEntityAlg("ParentNode");
        nodeEntity.setParent(parent);
        assertEquals(parent, nodeEntity.getParent());
    }

    @Test
    public void testGetConnections() {
        assertTrue(nodeEntity.getConnections().isEmpty());

        EdgeEntityAlg connection1 = new EdgeEntityAlg();
        EdgeEntityAlg connection2 = new EdgeEntityAlg();
        List<EdgeEntityAlg> connections = new ArrayList<>();
        connections.add(connection1);
        connections.add(connection2);

        nodeEntity.setConnections(connection1);
        nodeEntity.setConnections(connection2);

        assertEquals(connections, nodeEntity.getConnections());
    }

    @Test
    public void testCopyConstructor() {
        nodeEntity.setDistance(10.0);
        NodeEntityAlg parent = new NodeEntityAlg("ParentNode");
        nodeEntity.setParent(parent);

        NodeEntityAlg copy = new NodeEntityAlg(nodeEntity);

        assertNotSame(nodeEntity, copy);
        assertEquals(nodeEntity.getName(), copy.getName());
        assertEquals(nodeEntity.getDistance(), copy.getDistance());
        assertEquals(nodeEntity.getParent(), copy.getParent());
    }
}
