package com.example.demo.dto;

import com.example.demo.model.entity.EdgeEntity;

/**
 * A data transfer object (DTO) representing the deletion request for an edge entity.
 * It contains information about the edge to be deleted, such as its ID, starting node name, ending node name, and RPN.
 */
public class EdgeDTODelete {
    private Long id;
    private String rpn;
    private String startNode;
    private String endNode;

    /**
     * Constructs a new EdgeDTODelete with default values.
     */    public EdgeDTODelete() {
    
    }

    /**
     * Constructs an EdgeDTODelete from an existing EdgeEntity.
     *
     * @param edgeEntity The EdgeEntity from which to create the DTO.
     */
    public EdgeDTODelete(EdgeEntity edgeEntity) {
        this.id = edgeEntity.getId();
        this.startNode = edgeEntity.getStartNode().getName();
        this.endNode= edgeEntity.getEndNode().getName();
        this.rpn= edgeEntity.getRpn();
    }

    /**
     * Gets the ID of the edge to be deleted.
     *
     * @return The ID of the edge to be deleted.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the edge to be deleted.
     *
     * @param id The ID of the edge to be deleted.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the starting node of the edge to be deleted.
     *
     * @return The name of the starting node.
     */
    public String getStartNode() {
        return this.startNode;
    }

    /**
     * Sets the name of the starting node of the edge to be deleted.
     *
     * @param startNode The name of the starting node.
     */
    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    /**
     * Gets the name of the ending node of the edge to be deleted.
     *
     * @return The name of the ending node.
     */
    public String getEndNode() {
        return this.endNode;
    }
    
    /**
     * Sets the name of the ending node of the edge to be deleted.
     *
     * @param endNode The name of the ending node.
     */
    public void setEndNode(String endNode) {
        this.endNode= endNode;
    }
    
    /**
     * Gets the relative path name (RPN) of the edge to be deleted.
     *
     * @return The RPN of the edge to be deleted.
     */
    public String getRpn() {
        return (String) this.rpn;
         }

    /**
     * Sets the relative path name (RPN) of the edge to be deleted.
     *
     * @param rpn The RPN of the edge to be deleted.
     */
    public void setRpn(String rpn) {
        this.rpn = rpn;
    }

}