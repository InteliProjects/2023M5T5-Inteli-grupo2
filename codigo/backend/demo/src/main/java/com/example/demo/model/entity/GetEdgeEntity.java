package com.example.demo.model.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * Represents an entity for edges retrieved from a Neo4j graph database.
 */
@Node("GetEdgeEntity")
public class GetEdgeEntity {

    @Id
    private Long id;
    private String startNode;
    private String endNode;
    private String rpn;
    private Double weightgo;
    private Double weightrt;

    /**
     * Gets the unique identifier of the edge entity.
     *
     * @return The unique identifier of the edge.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the edge entity.
     *
     * @param id The unique identifier to set.
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
        return startNode;
    }

    /**
     * Sets the name of the starting node of the edge.
     *
     * @param startNode The name of the starting node to set.
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
        return endNode;
    }

    /**
     * Sets the name of the ending node of the edge.
     *
     * @param endNode The name of the ending node to set.
     */
    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }

    /**
     * Gets the relative path name (RPN) associated with the edge.
     *
     * @return The relative path name (RPN) of the edge.
     */
    public String getRpn() {
        return rpn;
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
     * Gets the weightgo value associated with the edge.
     *
     * @return The weightgo value of the edge.
     */
    public Double getWeightgo() {
        return weightgo;
    }

    /**
     * Sets the weightgo value associated with the edge.
     *
     * @param weightgo The weightgo value to set.
     */
    public void setWeightgo(Double weightgo) {
        this.weightgo = weightgo;
    }

    /**
     * Gets the weightrt value associated with the edge.
     *
     * @return The weightrt value of the edge.
     */
    public Double getWeightrt() {
        return weightrt;
    }

    /**
     * Sets the weightrt value associated with the edge.
     *
     * @param weightrt The weightrt value to set.
     */
    public void setWeightrt(Double weightrt) {
        this.weightrt = weightrt;
    }
}
