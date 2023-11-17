package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EdgeDTO;
import com.example.demo.model.entity.GetEdgeEntity;
import com.example.demo.service.EdgeService;

/**
 * A controller class that handles HTTP requests related to edge operations.
 */
@RestController
@RequestMapping("/edges")
@CrossOrigin(origins = "http://localhost:5174")
public class EdgeController {

    /** The service responsible for edge-related operations. */
    @Autowired
    private EdgeService edgeService;

    /**
     * Creates a new edge based on the provided edge DTO.
     *
     * @param edgeDTO The edge DTO containing edge information.
     * @return A ResponseEntity containing a success message indicating the edge creation.
     */
    @PostMapping()
    public ResponseEntity<String> createEdge(@RequestBody EdgeDTO edgeDTO){
        String createdEdge = edgeService.createEdge(edgeDTO);
        return ResponseEntity.ok(createdEdge);
    }

    /**
     * Executes a Neo4j query and returns the result as a list of maps.
     *
     * @return A list of maps representing the result of the Neo4j query.
     */
    @GetMapping
    public List<Map<String, Object>> executeNeo4jQuery() {
        return edgeService.executeNeo4jQuery();
    }

    @GetMapping("/find")
    public List<Map<String, Object>> executeNeo4jQueryRpn(@RequestParam String rpn) {
        return edgeService.executeNeo4jQueryRpn(rpn);
    }
    
    /**
     * Retrieves a list of all edges that start from a given node.
     *
     * @param startNode The name of the starting node.
     * @return A list of GetEdgeEntity objects representing the edges starting from the specified node.
     */
    @GetMapping("/{startNode}")
    public List<GetEdgeEntity> getAllEdgesByStartNode(@PathVariable String startNode) {
        return edgeService.getAllEdgesByStartNode(startNode);
    }
}