package com.example.demo.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeEntityTest {

    private EdgeEntity edgeEntity;

    @BeforeEach
    public void setUp() {
        edgeEntity = new EdgeEntity();
    }

    @Test
    public void testId() {
        edgeEntity.setId(1L);
        assertEquals(1L, edgeEntity.getId());
    }

    @Test
    public void testRpn() {
        edgeEntity.setRpn("TestRPN");
        assertEquals("TestRPN", edgeEntity.getRpn());
    }

    @Test
    public void testWeightGo() {
        edgeEntity.setWeightgo(2.5);
        assertEquals(2.5, edgeEntity.getWeightgo(), 0.001);
    }

    @Test
    public void testWeightRt() {
        edgeEntity.setWeightrt(1.8);
        assertEquals(1.8, edgeEntity.getWeightrt(), 0.001);
    }

    @Test
    public void testBidirectional() {
        edgeEntity.setBidirecional(true);
        assertTrue(edgeEntity.isBidirecional());
    }

    @Test
    public void testStartNode() {
        NodeEntity startNode = new NodeEntity();
        startNode.setName("StartNode");
        edgeEntity.setStartNode(startNode);
        assertEquals("StartNode", edgeEntity.getStartNode().getName());
    }

    @Test
    public void testEndNode() {
        NodeEntity endNode = new NodeEntity();
        endNode.setName("EndNode");
        edgeEntity.setEndNode(endNode);
        assertEquals("EndNode", edgeEntity.getEndNode().getName());
    }
}
