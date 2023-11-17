package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.*;

/**
 * Represents a node entity in the graph.
 */
@Node
public class NodeEntity {
    @Id @GeneratedValue
    private Long id;

    /** The name of the node. */
    private String name;

    /** The list of connections (edges) this node is part of. */
    private List<EdgeEntity> connections;

    /** The distance associated with the node. */
    private double distance;

    /** The parent node of this node in the graph. */
    private NodeEntity parent;

    /** The type of the node. */
    private String type;

    /** The relative path name (RPN) associated with the node. */
    private String rpn;

    /** Additional properties associated with the node. */
    @ConvertWith(converter = MapToStringConverter.class)
    private Map<String, Object> properties;

    /**
     * Default constructor initializes the properties map.
     */
    public NodeEntity() {
        this.properties = new HashMap<>();
    }

    /**
     * Constructor with a name parameter.
     *
     * @param name The name of the node.
     */
    public NodeEntity(String name){
        this.name = name;
        this.connections = new ArrayList<>();
    }

    /**
     * Get the unique identifier of the node.
     *
     * @return The node's identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the node.
     *
     * @param id The node's identifier.
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
     * Get the type of the node.
     *
     * @return The type of the node.
     */
    public String getType() {
        return (String) this.type;
    }

    /**
     * Set the type of the node.
     *
     * @param type The type of the node.
     */
    public void setType(String type) {
        this.type= type;
    }

    /**
     * Get the relative path name (RPN) associated with the node.
     *
     * @return The RPN of the node.
     */
    public String getRpn() {
        return this.rpn;
    }

    /**
     * Set the relative path name (RPN) associated with the node.
     *
     * @param rpn The RPN of the node.
     */
    public void setRpn(String rpn) {
        this.rpn = rpn;
    }
    
    /**
     * Get the additional properties associated with the node.
     *
     * @return The properties map.
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Set the additional properties associated with the node.
     *
     * @param properties The properties map.
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * Get the list of connections (edges) this node is part of.
     *
     * @return The list of connections.
     */
    public List<EdgeEntity> getConnections() {
        return connections;
    }

    /**
     * Set the list of connections (edges) this node is part of.
     *
     * @param connections The list of connections.
     */
    public void setConnections(List<EdgeEntity> connections) {
        this.connections = connections;
    }

    /**
     * Get the distance associated with the node.
     *
     * @return The distance.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Set the distance associated with the node.
     *
     * @param distance The distance.
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Get the parent node of this node in the graph.
     *
     * @return The parent node.
     */
    public NodeEntity getParent() {
        return parent;
    }

    /**
     * Set the parent node of this node in the graph.
     *
     * @param parent The parent node.
     */
    public void setParent(NodeEntity parent) {
        this.parent = parent;
    }

}