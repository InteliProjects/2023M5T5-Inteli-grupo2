package com.example.demo.dto;

import java.util.Map;

import org.springframework.data.neo4j.core.convert.ConvertWith;

import com.example.demo.model.entity.MapToStringConverter;
import com.example.demo.model.entity.NodeEntity;

/**
 * A Data Transfer Object (DTO) representing a NodeEntity with specified attributes.
 */
public class NodeDTO {
    /** The unique identifier of the node. */
    private Long id;

    /** The name of the node. */
    private String name;

    /** The type of the node. */
    private String type;

    /** The relative path name (RPN) associated with the node. */
    private String rpn;

    /** Additional properties associated with the node as a JSON string. */
    @ConvertWith(converter = MapToStringConverter.class)
    private Map<String, Object> properties;

    /**
     * Default constructor for NodeDTO.
     */
    public NodeDTO() {
    }

    /**
     * Constructor to create a NodeDTO from a NodeEntity.
     *
     * @param nodeEntity The NodeEntity to create the DTO from.
     */
    public NodeDTO(NodeEntity nodeEntity) {
        this.id = nodeEntity.getId();
        this.name = nodeEntity.getName();
        this.type= nodeEntity.getType();
        this.rpn= nodeEntity.getRpn();
        this.properties = nodeEntity.getProperties();

    }

    /**
     * Gets the unique identifier of the node.
     *
     * @return The node's unique identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the node.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the node.
     *
     * @return The node's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the node.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the node.
     *
     * @return The node's type.
     */
    public String getType() {
        return (String) this.type;
    }

    /**
     * Sets the type of the node.
     *
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type= type;
    }

    /**
     * Gets the relative path name (RPN) associated with the node.
     *
     * @return The node's RPN.
     */
    public String getRpn() {
        return this.rpn;
    }

    /**
     * Sets the relative path name (RPN) associated with the node.
     *
     * @param rpn The RPN to set.
     */
    public void setRpn(String rpn) {
        this.rpn = rpn;
    }
  
    /**
     * Gets the additional properties associated with the node.
     *
     * @return The node's properties as a map.
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Sets the additional properties associated with the node.
     *
     * @param properties The properties to set as a map.
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}