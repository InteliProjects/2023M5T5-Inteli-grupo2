package com.example.demo.dto;

import java.util.Map;

import org.springframework.data.neo4j.core.convert.ConvertWith;

import com.example.demo.model.entity.MapToStringConverter;
import com.example.demo.model.entity.NodeEntity;

/**
 * A Data Transfer Object (DTO) representing a NodeEntity for deletion with specified attributes.
 */
public class NodeDTODelete {

    /** The unique identifier of the node. */
    private Long id;

    /** The name of the node. */
    private String name;

    /** The relative path name (RPN) associated with the node. */
    private String rpn;

    /** Additional properties associated with the node as a JSON string. */
    @ConvertWith(converter = MapToStringConverter.class)
    private Map<String, Object> properties;

    /**
     * Default constructor for NodeDTODelete.
     */
    public NodeDTODelete() {
    }

    /**
     * Constructor to create a NodeDTODelete from a NodeEntity.
     *
     * @param nodeEntity The NodeEntity to create the DTO from.
     */
    public NodeDTODelete(NodeEntity nodeEntity) {
        this.id = nodeEntity.getId();
        this.name = nodeEntity.getName();
        this.rpn= nodeEntity.getRpn();

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
     * Gets the relative path name (RPN) associated with the node.
     *
     * @return The node's RPN.
     */
    public String getRpn() {
        return  this.rpn;
    }

    /**
     * Sets the relative path name (RPN) associated with the node.
     *
     * @param rpn The RPN to set.
     */
    public void setRpn(String rpn) {
        this.rpn = rpn;
    }
 
}