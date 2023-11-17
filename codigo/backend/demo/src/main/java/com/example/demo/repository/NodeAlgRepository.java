package com.example.demo.repository;

import com.example.demo.model.entity.NodeEntity;
import com.example.demo.model.entity.NodeEntityAlg;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing node entities in the application using Neo4j.
 */
@Repository
public interface NodeAlgRepository extends Neo4jRepository<NodeEntity, Long> {

    /**
     * Retrieves a list of all node entities in the Neo4j database.
     *
     * @return A list of all node entities.
     */
    @Query("MATCH (n) RETURN n")
    List<NodeEntity> findAllNodes();

    /**
     * Finds a node entity by its name.
     *
     * @param name The name of the node to find.
     * @return A NodeEntityAlg object representing the node with the specified name.
     */
    NodeEntityAlg findByName(String name);

    
}