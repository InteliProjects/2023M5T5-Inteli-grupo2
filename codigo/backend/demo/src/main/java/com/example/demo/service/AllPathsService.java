package com.example.demo.service;

import com.example.demo.model.entity.NodeEntityAlg;
import com.example.demo.model.entity.EdgeEntityAlg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for finding all paths between two nodes in a graph.
 */
@Service
public class AllPathsService {

    @Autowired
    private EdgeService edgeService;

    private Map<String, NodeEntityAlg> nodes = new HashMap<>();
    private Map<String, List<EdgeEntityAlg>> adjacencyList = new HashMap<>();

    private List<List<String>> allPaths = new ArrayList<>();

    /**
     * Builds a graph by fetching data from the database using EdgeService.
     */
    public void buildGraph() {
        List<Map<String, Object>> edgesData = edgeService.executeNeo4jQuery();

        for (Map<String, Object> edgeData : edgesData) {
            String startNodeName = (String) edgeData.get("startNode");
            String endNodeName = (String) edgeData.get("endNode");
            double weight = edgeData.get("r.weightgo") != null ? (double) edgeData.get("r.weightgo") : (double) edgeData.get("r.weightrt");
            double distance = edgeData.get("distance") != null ? (double) edgeData.get("distance") : 0.0; // Add distance field here

            NodeEntityAlg startNode = nodes.computeIfAbsent(startNodeName, NodeEntityAlg::new);
            NodeEntityAlg endNode = nodes.computeIfAbsent(endNodeName, NodeEntityAlg::new);

            EdgeEntityAlg edge = new EdgeEntityAlg();
            edge.setStartNode(startNode);
            edge.setEndNode(endNode);
            edge.setWeightgo(weight);
            edge.setDistance(distance); // Set distance

            startNode.getConnections().add(edge);

            // Update adjacency list
            adjacencyList.computeIfAbsent(startNodeName, key -> new ArrayList<>()).add(edge);
        }
    }

    /**
     * Finds all paths between two nodes using Depth-First Search (DFS).
     *
     * @param startNodeName The name of the starting node.
     * @param endNodeName   The name of the ending node.
     * @param maxDepth      The maximum depth for DFS.
     * @return A list of paths as lists of node names.
     */
    public List<List<String>> findAllPathsDFS(String startNodeName, String endNodeName, int maxDepth) {
        buildGraph();

        NodeEntityAlg startNode = nodes.get(startNodeName);
        NodeEntityAlg endNode = nodes.get(endNodeName);

        List<String> path = new ArrayList<>();

        allPaths.clear();

        findPathsDFS(startNode, endNode, path, maxDepth, 0);

        // Sort paths based on total distance
        allPaths.sort(Comparator.comparingDouble(this::calculateTotalDistance));

        return deduplicatePaths(allPaths, 3); // Deduplicate paths and return up to 3 of them
    }

    /**
     * Calculate the total distance of a path.
     *
     * @param path The path as a list of node names.
     * @return The total distance of the path.
     */
    private double calculateTotalDistance(List<String> path) {
        double totalDistance = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            NodeEntityAlg currentNode = nodes.get(path.get(i));
            NodeEntityAlg nextNode = nodes.get(path.get(i + 1));
            for (EdgeEntityAlg edge : adjacencyList.get(currentNode.getName())) {
                if (edge.getEndNode().equals(nextNode)) {
                    totalDistance += edge.getDistance();
                    break;
                }
            }
        }
        return totalDistance;
    }

    /**
     * Depth-First Search (DFS) to find paths between two nodes.
     *
     * @param start     The starting node.
     * @param end       The ending node.
     * @param localPath The current path being explored.
     * @param maxDepth  The maximum depth for DFS.
     * @param depth     The current depth in DFS.
     */
    private void findPathsDFS(
            NodeEntityAlg start,
            NodeEntityAlg end,
            List<String> localPath,
            int maxDepth,
            int depth
    ) {
        if (depth > maxDepth) {
            return;
        }

        String nodeName = start.getName();

        // Check if the node is already in the current path
        if (localPath.contains(nodeName)) {
            return;
        }

        localPath.add(nodeName);

        if (start.equals(end)) {
            allPaths.add(new ArrayList<>(localPath));
        } else if (depth < maxDepth) {
            List<EdgeEntityAlg> edges = adjacencyList.get(nodeName);

            // Sort edges based on distance to apply shortest path heuristic
            edges.sort(Comparator.comparingDouble(EdgeEntityAlg::getDistance));

            for (EdgeEntityAlg e : edges) {
                NodeEntityAlg node = e.getEndNode();
                findPathsDFS(node, end, localPath, maxDepth, depth + 1);
            }
        }

        localPath.remove(localPath.size() - 1); // Remove the last node from the path
    }

    /**
     * Deduplicate paths and return up to the specified maximum number of paths.
     *
     * @param paths    The list of paths to deduplicate.
     * @param maxPaths The maximum number of paths to return.
     * @return A list of deduplicated paths.
     */
    private List<List<String>> deduplicatePaths(List<List<String>> paths, int maxPaths) {
        Set<List<String>> uniquePaths = new HashSet<>(paths);
        List<List<String>> deduplicatedPaths = new ArrayList<>(uniquePaths);

        if (deduplicatedPaths.size() > maxPaths) {
            deduplicatedPaths = deduplicatedPaths.subList(0, maxPaths);
        }

        return deduplicatedPaths;
    }
}
