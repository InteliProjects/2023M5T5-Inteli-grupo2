package com.example.demo.model.entity;

import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

/**
 * Represents an entity for edges in a graph.
 */
@RelationshipProperties
public class EdgeEntity {

    @Id @GeneratedValue
    private Long id;

    /** The relative path name (RPN) associated with the edge. */
    private String rpn;

    /** The starting node of the edge. */
    private NodeEntity startNode;

    @TargetNode
    /** The ending node of the edge. */
    private NodeEntity endNode;

    /** The weight of the edge in the "go" direction. */
    private double weightgo;
    
    /** The weight of the edge in the "return" direction. */
    private double weightrt;

    /** Indicates if the edge is bidirectional. */
    private boolean bidirecional;

    /**
     * Default constructor for EdgeEntity.
     */
    public EdgeEntity(){

    }
        
    /**
     * Gets the unique identifier of the edge.
     *
     * @return The unique identifier of the edge.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the relative path name (RPN) associated with the edge.
     *
     * @return The relative path name (RPN) associated with the edge.
     */
    public String getRpn() {
        return rpn;
    }

    /**
     * Gets the weight of the edge in the "go" direction.
     *
     * @return The weight of the edge in the "go" direction.
     */
    public double getWeightgo() {
        return weightgo;
    }

    /**
     * Gets the weight of the edge in the "return" direction.
     *
     * @return The weight of the edge in the "return" direction.
     */
    public double getWeightrt() {
        return weightrt;
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
     * Gets the starting node of the edge.
     *
     * @return The starting node of the edge.
     */
    public NodeEntity getStartNode() {
        return startNode;
    }

    /**
     * Gets the ending node of the edge.
     *
     * @return The ending node of the edge.
     */
    public NodeEntity getEndNode() {
        return endNode;
    }

    /**
     * Sets the unique identifier of the edge.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the relative path name (RPN) associated with the edge.
     *
     * @param rpn The relative path name (RPN) to set.
     */
    public void setRpn(String rpn) {
        this.rpn = rpn;
    }

    /**
     * Sets the weight of the edge in the "go" direction.
     *
     * @param weightgo The weight value to set for the "go" direction.
     */
    public void setWeightgo(double weightgo) {
        this.weightgo = weightgo;
    }

    /**
     * Sets the weight of the edge in the "return" direction.
     *
     * @param weightrt The weight value to set for the "return" direction.
     */
    public void setWeightrt(double weightrt) {
        this.weightrt = weightrt;
    }

    /**
     * Sets the starting node of the edge.
     *
     * @param startNode The starting node to set for the edge.
     */
    public void setStartNode(NodeEntity startNode) {
        this.startNode = startNode;
    }

    /**
     * Sets the ending node of the edge.
     *
     * @param endNode The ending node to set for the edge.
     */
    public void setEndNode(NodeEntity endNode) {
        this.endNode = endNode;
    }

    /**
     * Sets whether the edge is bidirectional.
     *
     * @param bidirectional True if the edge is bidirectional, false otherwise.
     */
    public void setBidirecional(boolean bidirecional) {
        this.bidirecional = bidirecional;
    }

}