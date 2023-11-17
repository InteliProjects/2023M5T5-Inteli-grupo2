package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.entity.NodeEntityAlg;

/**
 * A Data Transfer Object (DTO) representing a path between nodes and its shortest distance.
 */
public class PathDTO {
    /** The list of nodes that make up the path. */
    private List<NodeEntityAlg> path;

    /** The shortest distance of the path. */
    private double shortDistance;

    /**
     * Default constructor for PathDTO.
     */
    public PathDTO() {
    }

    // public PathDTO(List<Object> list, double positiveInfinity) {
   
    // }

    /**
     * Gets the list of nodes that make up the path.
     *
     * @return The list of nodes in the path.
     */
    public List<NodeEntityAlg> getPath() {
        return path;
    }

    /**
     * Sets the list of nodes that make up the path.
     *
     * @param path The list of nodes to set as the path.
     */
    public void setPath(List<NodeEntityAlg> path) {
        this.path = path;
    }

    /**
     * Gets the shortest distance of the path.
     *
     * @return The shortest distance.
     */
    public double getShortDistance() {
        return shortDistance;
    }

    /**
     * Sets the shortest distance of the path.
     *
     * @param shortDistance The shortest distance to set.
     */
    public void setShortDistance(double shortDistance) {
        this.shortDistance = shortDistance;
    }

}
