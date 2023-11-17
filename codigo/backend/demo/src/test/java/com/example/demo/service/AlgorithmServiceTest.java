package com.example.demo.service;

import com.example.demo.model.entity.NodeEntityAlg;
import com.example.demo.model.entity.EdgeEntityAlg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlgorithmServiceTest {

    private AlgorithmService algorithmService;

    @Mock
    private EdgeService edgeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        algorithmService = new AlgorithmService();
        algorithmService.edgeService = edgeService;
    }

    @Test
    public void testBuildGraph() {
        // Defina o comportamento do serviço de borda fictícia para retornar alguns dados de borda simulados.
        List<Map<String, Object>> simulatedEdgesData = new ArrayList<>();
        Map<String, Object> edgeData1 = new HashMap<>();
        edgeData1.put("startNode", "A");
        edgeData1.put("endNode", "B");
        edgeData1.put("r.weightgo", 5.0);
        simulatedEdgesData.add(edgeData1);

        when(edgeService.executeNeo4jQuery()).thenReturn(simulatedEdgesData);

        // Execute o método buildGraph
        algorithmService.buildGraph();

        // Verifique se o gráfico foi construído corretamente
        Map<String, NodeEntityAlg> nodes = algorithmService.getNodes();
        assertEquals(2, nodes.size());

        NodeEntityAlg nodeA = nodes.get("A");
        NodeEntityAlg nodeB = nodes.get("B");

        assertNotNull(nodeA);
        assertNotNull(nodeB);

        assertEquals(1, nodeA.getConnections().size());
        assertEquals(0, nodeB.getConnections().size());
    }

    @Test
    public void testFindShortestPath() {
        // Crie nós fictícios e arestas fictícias para testar o cálculo do caminho mais curto.
        NodeEntityAlg nodeA = new NodeEntityAlg("A");
        NodeEntityAlg nodeB = new NodeEntityAlg("B");
        NodeEntityAlg nodeC = new NodeEntityAlg("C");
        NodeEntityAlg nodeD = new NodeEntityAlg("D");

        EdgeEntityAlg edgeAB = new EdgeEntityAlg(nodeA, nodeB, 2.0);
        EdgeEntityAlg edgeAC = new EdgeEntityAlg(nodeA, nodeC, 1.0);
        EdgeEntityAlg edgeBC = new EdgeEntityAlg(nodeB, nodeC, 1.0);
        EdgeEntityAlg edgeCD = new EdgeEntityAlg(nodeC, nodeD, 3.0);

        nodeA.getConnections().addAll(Arrays.asList(edgeAB, edgeAC));
        nodeB.getConnections().add(edgeBC);
        nodeC.getConnections().add(edgeCD);

        algorithmService.getNodes().put("A", nodeA);
        algorithmService.getNodes().put("B", nodeB);
        algorithmService.getNodes().put("C", nodeC);
        algorithmService.getNodes().put("D", nodeD);

        // Execute o método findShortestPath para encontrar o caminho mais curto de A para D
        List<String> shortestPath = algorithmService.findShortestPath("A", "D");

        // Verifique se o caminho mais curto é [A, C, D] com peso total 4.0
        assertEquals(Arrays.asList("A", "C", "D"), shortestPath);
    }
}

