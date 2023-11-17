package com.example.demo.dto;

import com.example.demo.model.entity.EdgeEntity;

/**
 * A data transfer object (DTO) representing an edge entity.
 * It contains information about the edge, such as its ID, starting node name, ending node name, RPN, weights, and bidirectional status.
 */
public class EdgeDTO {
    private Long id;
    private String startNode;
    private String endNode;
    private String rpn;
    private double weightgo;
    private double weightrt;
    private boolean bidirecional;

    /**
     * Constructs a new EdgeDTO with default values.
     */
    public EdgeDTO() {
    }

    /**
     * Constructs an EdgeDTO from an existing EdgeEntity.
     *
     * @param edgeEntity The EdgeEntity from which to create the DTO.
     */
    public EdgeDTO(EdgeEntity edgeEntity) {
        this.id = edgeEntity.getId();
        this.startNode = edgeEntity.getStartNode().getName();
        this.endNode= edgeEntity.getEndNode().getName();
        this.rpn= edgeEntity.getRpn();
        this.weightrt= edgeEntity.getWeightrt();
        this.weightgo= edgeEntity.getWeightgo();
        this.bidirecional= edgeEntity.isBidirecional();
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
     * Gets the name of the starting node of the edge.
     *
     * @return The name of the starting node.
     */
    public String getStartNode() {
        return this.startNode;
    }

    /**
     * Sets the name of the starting node of the edge.
     *
     * @param startNode The name of the starting node.
     */
    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    /**
     * Gets the name of the ending node of the edge.
     *
     * @return The name of the ending node.
     */
    public String getEndNode() {
        return this.endNode;
    }
    
    /**
     * Sets the name of the ending node of the edge.
     *
     * @param endNode The name of the ending node.
     */
    public void setEndNode(String endNode) {
        this.endNode= endNode;
    }
    
    /**
     * Gets the weight of the edge in the "go" direction.
     *
     * @return The weight in the "go" direction.
     */
    public double getWeightgo() {
        return weightgo;
    }

    /**
     * Sets the weight of the edge in the "go" direction.
     *
     * @param weightgo The weight in the "go" direction.
     */
    public void setWeightgo(double weightgo) {
        this.weightgo = weightgo;
    }

    /**
     * Gets the weight of the edge in the "return" direction.
     *
     * @return The weight in the "return" direction.
     */
    public double getWeightrt() {
        return weightrt;
    }

    /**
     * Sets the weight of the edge in the "return" direction.
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

    /**
     * Checks if the edge is bidirectional.
     *
     * @return True if the edge is bidirectional, false otherwise.
     */
    public boolean isBidirecional(){
        return bidirecional;
    }

    /**
     * Sets the bidirectional status of the edge.
     *
     * @param bidirecional True if the edge is bidirectional, false otherwise.
     */
    public void setBidirecional(boolean bidirecional) {
        this.bidirecional = bidirecional;
    }

}