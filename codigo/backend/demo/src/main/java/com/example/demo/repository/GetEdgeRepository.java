package com.example.demo.repository;


import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.GetEdgeEntity;

/**
 * Repository interface for managing edge entities in the application using Neo4j.
 */
@Repository
public interface GetEdgeRepository extends Neo4jRepository<GetEdgeEntity, Long> {
    /**
     * Retrieves a list of all edge entities in the Neo4j database.
     *
     * @return A list of all edge entities.
     */
    @Query("MATCH (a:NodeEntity)-[r:TUBULAÇÃO]->(b:NodeEntity) RETURN a.name AS startNode, b.name AS endNode, r.rpn, r.weightgo, r.weightrt")
    List<GetEdgeEntity> getAllEdges();


    // GetEdgeEntity findByName(String name);

    /**
     * Retrieves a list of edge entities starting from a specified node.
     *
     * @param startNode The name of the start node for which to retrieve associated edges.
     * @return A list of edge entities starting from the specified start node.
     */
    @Query("MATCH (a:NodeEntity {name: $startNode})-[r:TUBULAÇÃO]->(b:NodeEntity) RETURN a.name AS startNode, b.name AS endNode, r.rpn AS rpn, r.weightgo AS weightgo, r.weightrt AS weightrt")
    List<GetEdgeEntity> getAllEdgesByStartNode(@Param("startNode") String startNode);

}