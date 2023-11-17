package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.*;

/**
 * Represents a node entity for the Djikstra algorithm.
 */
@Node
public class NodeEntityAlg {
    @Id @GeneratedValue
    private Long id;

    /** Node name. */
    private String name;

    /** List of connections(neighbours) */
    private List<EdgeEntityAlg> connections;

    /** The relative path name (RPN) associated with the node. */
    private String rpn;

    /** The distance value associated with the node, initialized to positive infinity. */
    private double distance= Double.POSITIVE_INFINITY;

    /** The parent node of this node in the graph. */
    private NodeEntityAlg parent;

    /**
     * Default constructor for NodeEntityAlg.
     */
    public NodeEntityAlg(){

    }

    /**
     * Creates a NodeEntityAlg with the specified name.
     *
     * @param name The name of the node.
     */
    public NodeEntityAlg(String name){
        this.name = name;
        this.connections = new ArrayList<>();
    }
    
    /**
     * Creates a copy of the given NodeEntityAlg.
     *
     * @param node The NodeEntityAlg to copy.
     */
    public NodeEntityAlg(NodeEntityAlg node){
        this.name = node.name;
        this.connections = new ArrayList<>(node.connections);
        this.distance = node.distance;
        this.parent = node.parent;
    }

    /**
     * Get the ID of the node.
     *
     * @return The ID of the node.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the node.
     *
     * @param id The ID of the node.
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Get the name of the node.
     *
     * @return The name of the node.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the node.
     *
     * @param name The name of the node.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the list of connections from this node.
     *
     * @return The list of connections from this node.
     */
    public List<EdgeEntityAlg> getConnections() {
        return connections;
    }

    /**
     * Get the distance value associated with the node.
     *
     * @return The distance value associated with the node.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Set the distance value associated with the node.
     *
     * @param distance The distance value to set.
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Get the parent node of this node in the graph.
     *
     * @return The parent node of this node.
     */
    public NodeEntityAlg getParent() {
        return parent;
    }

    /**
     * Set the parent node of this node in the graph.
     *
     * @param parent The parent node to set.
     */
    public void setParent(NodeEntityAlg parent) {
        this.parent = parent;
    }

    /**
    * Adds a connection to this node.
    *
    * @param connection The edge entity representing the connection to add.
    */
    public void setConnections(EdgeEntityAlg connections) {
        this.connections.add(connections);
    }

    public String getRpn() {
        return rpn;
    }

    public void setRpn(String rpn) {
        this.rpn = rpn;
    }

    

}
    


