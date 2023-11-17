package com.example.demo.model.entity;

import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

/**
 * Represents an entity for edges in a graph with additional properties, used in algorithms(Djikstra).
 */
@RelationshipProperties
public class EdgeEntityAlg {

    @Id @GeneratedValue
    private Long id;

    private List<EdgeEntityAlg> edges = new ArrayList<>();

    /** The starting node of the edge. */
    private NodeEntityAlg startNode;
    
    @TargetNode
    /** The ending node of the edge. */
    private NodeEntityAlg endNode;

    /** The weight of the edge in the "go" direction. */
    private Double weightgo;

    /** The weight of the edge in the "return" direction. */
    private Double weightrt;


    /**
     * Default constructor for EdgeEntityAlg.
     */
    public EdgeEntityAlg(){

    }

    /**
     * Constructor for EdgeEntityAlg with starting and ending nodes and a weight value.
     *
     * @param startNode The starting node of the edge.
     * @param endNode   The ending node of the edge.
     * @param weight    The weight value of the edge in both "go" and "return" directions.
     */
    public EdgeEntityAlg(NodeEntityAlg startNode, NodeEntityAlg endNode, double weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weightgo = weight;
        this.weightrt = weight;
    }

    /**
     * Gets the weight value of the edge in the "go" direction.
     *
     * @return The weight value of the edge in the "go" direction.
     */
    public Double getWeightgo() {
        return weightgo;
    }

    /**
     * Gets the weight value of the edge in the "return" direction.
     *
     * @return The weight value of the edge in the "return" direction.
     */
    public Double getWeightrt() {
        return weightrt;
    }

    /**
     * Gets the list of edges associated with this edge entity.
     *
     * @return The list of edges associated with this edge entity.
     */
    public List<EdgeEntityAlg> getEdges() {
        return edges;
    }
 
    /**
     * Gets the starting node of the edge.
     *
     * @return The starting node of the edge.
     */
    public NodeEntityAlg getStartNode() {
        return startNode;
    }

    /**
     * Gets the ending node of the edge.
     *
     * @return The ending node of the edge.
     */
    public NodeEntityAlg getEndNode() {
        return endNode;
    }
    
    /**
     * Sets the weight value of the edge in the "go" direction.
     *
     * @param weightgo The weight value to set for the "go" direction.
     */
    public void setWeightgo(Double weightgo) {
        this.weightgo = weightgo;
    }

    /**
     * Sets the weight value of the edge in the "return" direction.
     *
     * @param weightrt The weight value to set for the "return" direction.
     */
    public void setWeightrt(Double weightrt) {
        this.weightrt = weightrt;
    }

    /**
     * Sets the starting node of the edge.
     *
     * @param startNode The starting node to set for the edge.
     */
    public void setStartNode(NodeEntityAlg startNode) {
        this.startNode = startNode;
    }

    /**
     * Sets the ending node of the edge.
     *
     * @param endNode The ending node to set for the edge.
     */
    public void setEndNode(NodeEntityAlg endNode) {
        this.endNode = endNode;
    }

    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
}
