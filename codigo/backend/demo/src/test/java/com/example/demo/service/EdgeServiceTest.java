package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.EdgeDTO;
import com.example.demo.dto.EdgeDTODelete;
import com.example.demo.repository.EdgeRepository;
import com.example.demo.repository.GetEdgeRepository;

public class EdgeServiceTest {

    @Autowired
    private EdgeService edgeService;

    @Mock
    private EdgeRepository edgeRepository;

    @Mock
    private GetEdgeRepository getEdgeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        edgeService = new EdgeService();
        edgeService.setEdgeRepository(edgeRepository);
        edgeService.setGetEdgeRepository(getEdgeRepository);
    }

    @Test
    public void testCreateEdge() {
        // Simulate the creation of an edge
        EdgeDTO edgeDTO = new EdgeDTO();
        edgeDTO.setStartNode("NodeA");
        edgeDTO.setEndNode("NodeB");
        edgeDTO.setRpn("RPN123");
        edgeDTO.setWeightgo(10.0);
        edgeDTO.setWeightrt(15.0);
        edgeDTO.setBidirecional(true);

        String result = edgeService.createEdge(edgeDTO);

        assertEquals("Aresta Bidirecional criada", result);
    }

    @Test
    public void testDeleteEdge() {
        // Simulate the deletion of an edge
        EdgeDTODelete edgeDTODelete = new EdgeDTODelete();
        edgeDTODelete.setStartNode("NodeA");
        edgeDTODelete.setEndNode("NodeB");
        edgeDTODelete.setRpn("RPN123");

        String result = edgeService.deleteEdge(edgeDTODelete);

        assertEquals("Aresta deletada", result);
    }
}

