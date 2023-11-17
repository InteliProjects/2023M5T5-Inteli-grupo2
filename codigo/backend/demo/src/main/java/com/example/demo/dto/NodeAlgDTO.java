package com.example.demo.dto;

/**
 * Data Transfer Object (DTO) representing a node in a graph with basic attributes.
 */
public class NodeAlgDTO {
    /** The unique identifier of the node. */
    private Long id;

    /** The name of the node. */
    private String name;

    /**
     * Default constructor for NodeAlgDTO.
     */
    public NodeAlgDTO(){      
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
        return name;
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
     * Sets the weight (distance) associated with the node.
     *
     * @param distance The weight (distance) to set.
     */
    public void setWeight(double distance) {
    }    

}
