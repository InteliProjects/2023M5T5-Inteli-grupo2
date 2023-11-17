package com.example.demo.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeEntityAlgTest {

    private EdgeEntityAlg edgeEntityAlg;
    private NodeEntityAlg startNode;
    private NodeEntityAlg endNode;

    @BeforeEach
    public void setUp() {
        startNode = new NodeEntityAlg();
        endNode = new NodeEntityAlg();
        edgeEntityAlg = new EdgeEntityAlg(startNode, endNode, 5.0);
    }

    @Test
    public void testGetWeightgo() {
        assertEquals(5.0, edgeEntityAlg.getWeightgo());
        edgeEntityAlg.setWeightgo(10.0);
        assertEquals(10.0, edgeEntityAlg.getWeightgo());
    }

    @Test
    public void testGetWeightrt() {
        assertEquals(5.0, edgeEntityAlg.getWeightrt());
        edgeEntityAlg.setWeightrt(8.0);
        assertEquals(8.0, edgeEntityAlg.getWeightrt());
    }

    @Test
    public void testGetEdges() {
        assertNotNull(edgeEntityAlg.getEdges());
        assertTrue(edgeEntityAlg.getEdges().isEmpty());
    }

    @Test
    public void testGetStartNode() {
        assertEquals(startNode, edgeEntityAlg.getStartNode());
        NodeEntityAlg newStartNode = new NodeEntityAlg();
        edgeEntityAlg.setStartNode(newStartNode);
        assertEquals(newStartNode, edgeEntityAlg.getStartNode());
    }

    @Test
    public void testGetEndNode() {
        assertEquals(endNode, edgeEntityAlg.getEndNode());
        NodeEntityAlg newEndNode = new NodeEntityAlg();
        edgeEntityAlg.setEndNode(newEndNode);
        assertEquals(newEndNode, edgeEntityAlg.getEndNode());
    }
}
