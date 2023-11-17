package com.example.demo.dto;

/**
 * A data transfer object (DTO) representing the response for an edge entity.
 * It contains information about the edge, such as its ID, starting node, ending node, weights, and RPN.
 */
public class EdgeResponseDTO {
    private Long id;
    private String rpn;
    private String startNode;
    private String endNode;
    private double weightgo;
    private double weightrt;

    /**
     * Constructs a new EdgeResponseDTO with default values.
     */
    public EdgeResponseDTO() {
    }

    /**
     * Gets the ID of the edge.
     *
     * @return The ID of the edge.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the edge.
     *
     * @param id The ID of the edge.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the starting node.
     *
     * @return The name of the starting node.
     */
    public String getStartNode() {
        return this.startNode;
    }

    /**
     * Sets the name of the starting node.
     *
     * @param startNode The name of the starting node.
     */
    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    /**
     * Gets the name of the ending node.
     *
     * @return The name of the ending node.
     */
    public String getEndNode() {
        return this.endNode;
    }
       
    /**
     * Sets the name of the ending node.
     *
     * @param endNode The name of the ending node.
     */
    public void setEndNode(String endNode) {
        this.endNode= endNode;
    }
    
    /**
     * Gets the weight in the "go" direction.
     *
     * @return The weight in the "go" direction.
     */
    public double getWeightgo() {
        return weightgo;
    }

    /**
     * Sets the weight in the "go" direction.
     *
     * @param weightgo The weight in the "go" direction.
     */
    public void setWeightgo(double weightgo) {
        this.weightgo = weightgo;
    }

    /**
     * Gets the weight in the "return" direction.
     *
     * @return The weight in the "return" direction.
     */
    public double getWeightrt() {
        return weightrt;
    }

    /**
     * Sets the weight in the "return" direction.
     *
     * @param weightrt The weight in the "return" direction.
     */
    public void setWeightrt(double weightrt) {
        this.weightrt = weightrt;
    }

    /**
     * Gets the relative path name (RPN) of the edge.
     *
     * @return The RPN of the edge.
     */
    public String getRpn() {
        return (String) this.rpn;
    }

    /**
     * Sets the relative path name (RPN) of the edge.
     *
     * @param rpn The RPN of the edge.
     */
    public void setRpn(String rpn) {
        this.rpn = rpn;
    }

}