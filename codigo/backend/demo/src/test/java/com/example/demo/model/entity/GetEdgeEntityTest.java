package com.example.demo.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetEdgeEntityTest {

    private GetEdgeEntity edgeEntity;

    @BeforeEach
    public void setUp() {
        edgeEntity = new GetEdgeEntity();
    }

    @Test
    public void testGetAndSetId() {
        assertNull(edgeEntity.getId());

        edgeEntity.setId(1L);
        assertEquals(1L, edgeEntity.getId());
    }

    @Test
    public void testGetAndSetStartNode() {
        assertNull(edgeEntity.getStartNode());

        edgeEntity.setStartNode("StartNode");
        assertEquals("StartNode", edgeEntity.getStartNode());
    }

    @Test
    public void testGetAndSetEndNode() {
        assertNull(edgeEntity.getEndNode());

        edgeEntity.setEndNode("EndNode");
        assertEquals("EndNode", edgeEntity.getEndNode());
    }

    @Test
    public void testGetAndSetRpn() {
        assertNull(edgeEntity.getRpn());

        edgeEntity.setRpn("RPN123");
        assertEquals("RPN123", edgeEntity.getRpn());
    }

    @Test
    public void testGetAndSetWeightgo() {
        assertNull(edgeEntity.getWeightgo());

        edgeEntity.setWeightgo(5.0);
        assertEquals(5.0, edgeEntity.getWeightgo());
    }

    @Test
    public void testGetAndSetWeightrt() {
        assertNull(edgeEntity.getWeightrt());

        edgeEntity.setWeightrt(3.0);
        assertEquals(3.0, edgeEntity.getWeightrt());
    }

}
