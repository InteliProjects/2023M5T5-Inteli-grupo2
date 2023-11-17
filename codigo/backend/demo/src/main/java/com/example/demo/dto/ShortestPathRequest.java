package com.example.demo.dto;

/**
 * A request object for finding the shortest path between two nodes.
 */
public class ShortestPathRequest {
    /** The starting node for the path. */
    private String startNode;

    /** The ending node for the path. */
    private String endNode;

    /**
     * Default constructor for ShortestPathRequest.
     */
    ShortestPathRequest(){

    }

    /**
     * Gets the starting node for the path.
     *
     * @return The starting node.
     */
    public String getStartNode() {
        return startNode;
    }

    /**
     * Sets the starting node for the path.
     *
     * @param startNode The starting node to set.
     */
    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    /**
     * Gets the ending node for the path.
     *
     * @return The ending node.
     */
    public String getEndNode() {
        return endNode;
    }

    /**
     * Sets the ending node for the path.
     *
     * @param endNode The ending node to set.
     */
    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }
    
}
