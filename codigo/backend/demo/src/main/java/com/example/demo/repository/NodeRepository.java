package com.example.demo.repository;

import com.example.demo.model.entity.NodeEntity;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing node entities in the application using Neo4j.
 */
@Repository
public interface NodeRepository extends Neo4jRepository<NodeEntity, Long> {

    /**
     * Retrieves a list of all node entities in the Neo4j database.
     *
     * @return A list of all node entities.
     */
    @Query("MATCH (n) RETURN n")
    List<NodeEntity> findAllNodes();

    /**
     * Deletes a node entity with a specific name and RPN (Relative Positioning Number).
     *
     * @param name The name of the node to delete.
     * @param rpn  The RPN of the node to delete.
     * @return A list of deleted node entities.
     */
    @Query("MATCH (n) WHERE n.name = $name AND n.rpn = $rpn DELETE n")
    List<NodeEntity> deleteNode(String name, String rpn);

    List<NodeEntity> findByRpn(String rpn);
    
}