package com.example.demo.dto;

/**
 * Data Transfer Object (DTO) representing a request with start and end nodes.
 */
public class RequestDTO {

    /** The starting node for the request. */
    private String startNode;

    /** The ending node for the request. */
    private String endNode;

    /**
     * Default constructor for RequestDTO.
     */
    public RequestDTO() {
    }

    /**
     * Gets the starting node for the request.
     *
     * @return The starting node.
     */
    public String getStartNode() {
        return startNode;
    }

    /**
     * Sets the starting node for the request.
     *
     * @param startNode The starting node to set.
     */
    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    /**
     * Gets the ending node for the request.
     *
     * @return The ending node.
     */
    public String getEndNode() {
        return endNode;
    }

    /**
     * Sets the ending node for the request.
     *
     * @param endNode The ending node to set.
     */
    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }
        
}
