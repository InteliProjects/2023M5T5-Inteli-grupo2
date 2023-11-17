package com.example.demo.model.entity;

import java.util.ArrayList;
import java.util.List;

public class PathEntity {
    private List<NodeEntityAlg> nodes;
    private List<EdgeEntityAlg> edges;

    public PathEntity() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void setNodes(NodeEntityAlg node) {
        nodes.add(node);
    }

    public void setEdges(EdgeEntityAlg edge) {
        edges.add(edge);
    }

    public List<NodeEntityAlg> getNodes() {
        return nodes;
    }

    public List<EdgeEntityAlg> getEdges() {
        return edges;
    }
}