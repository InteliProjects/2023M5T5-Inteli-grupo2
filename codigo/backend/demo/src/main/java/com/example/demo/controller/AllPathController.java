package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.entity.PathEntity;
import com.example.demo.repository.GetEdgeRepository;
import com.example.demo.repository.NodeAlgRepository;
import com.example.demo.service.AlgorithmService;
import com.example.demo.service.AllPathsService;

import jakarta.annotation.PostConstruct;

import java.util.List;

/**
 * A controller class responsible for handling requests related to finding all paths between two nodes in a graph.
 */
@RestController
@RequestMapping("/allpaths")
@CrossOrigin(origins = "http://localhost:5174")
public class AllPathController {

    @Autowired
    private AllPathsService allPathsService;

    @Autowired
    private NodeAlgRepository nodeRepository;

    @Autowired
    private GetEdgeRepository getEdgeRepository;

    /**
     * Finds all paths between two nodes in the graph using a depth-limited depth-first search.
     *
     * @param startNodeName The name of the starting node.
     * @param endNodeName   The name of the destination node.
     * @param maxDepth      The maximum depth for the depth-first search.
     * @return A list of lists, where each inner list represents a path between the given nodes.
     */
    @GetMapping()
    public List<List<String>> findPaths(
            @RequestParam String startNodeName,
            @RequestParam String endNodeName,
            @RequestParam int maxDepth) {
        return allPathsService.findAllPathsDFS(startNodeName, endNodeName, maxDepth);
    }
}
