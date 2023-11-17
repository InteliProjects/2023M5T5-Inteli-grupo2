package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.EdgeEntity;

/**
 * Repository interface for managing edge entities in the application using Neo4j.
 */
@Repository
public interface EdgeRepository extends Neo4jRepository<EdgeEntity, Long> {

    /**
     * Creates a unidirectional edge (relationship) between two nodes with specified properties.
     *
     * @param startNode The name of the start node.
     * @param endNode   The name of the end node.
     * @param weightgo  The weightgo property for the edge.
     * @param rpn       The rpn property for the edge.
     * @return The created edge entity.
     */
    @Query("MATCH (a:NodeEntity {name: $startNode}), (b:NodeEntity {name: $endNode}) CREATE (a)-[r:TUBULAÇÃO]->(b) FOREACH (ignoreMe IN CASE WHEN $weightgo IS NOT NULL THEN [1] ELSE [] END | SET r.weightgo = $weightgo) SET r.rpn = $rpn")   
    EdgeEntity createEdgeDic(@Param("startNode") String startNode, @Param("endNode") String endNode, @Param("weightgo") double weightgo, @Param("rpn") String rpn);

    /**
     * Creates a bidirectional edge (relationship) between two nodes with specified properties.
     *
     * @param startNode The name of the start node.
     * @param endNode   The name of the end node.
     * @param weightgo  The weightgo property for the edge.
     * @param weightrt  The weightrt property for the edge.
     * @param rpn       The rpn property for the edge.
     * @return The created edge entity.
     */
    @Query("MATCH (a:NodeEntity {name: $startNode}), (b:NodeEntity {name: $endNode}) CREATE (a)-[r1:TUBULAÇÃO]->(b) SET r1.weightgo = $weightgo, r1.rpn = $rpn CREATE (b)-[r2:TUBULAÇÃO]->(a) SET r2.weightrt = $weightrt, r2.rpn = $rpn")
    EdgeEntity createEdgeBiDic(@Param("startNode") String startNode, @Param("endNode") String endNode, @Param("weightgo") double weightgo, @Param("weightrt") double weightrt, @Param("rpn") String rpn);

    // @Query("MATCH (source:NodeEntity {name: $source})-[r]-(target:NodeEntity {name: $target}) RETURN r")
    // EdgeEntity getEdge(@Param("source") String source, @Param("target") String target);

    /**
     * Deletes an edge (relationship) between two nodes with the specified properties.
     *
     * @param startNode The name of the start node.
     * @param endNode   The name of the end node.
     * @param rpn       The rpn property of the edge to delete.
     * @return The deleted edge entity.
     */
    @Query("MATCH (a:NodeEntity {name: $startNode})-[r]-(b:NodeEntity {name: $endNode}) WHERE r.rpn = $rpn DETACH DELETE r")
    EdgeEntity deleteEdge(@Param("startNode") String startNode, @Param("endNode") String endNode, @Param("rpn") String rpn);

    // @Query("MATCH (a:NodeEntity {name: $startNode})-[r:TUBULAÇÃO]->(b:NodeEntity) RETURN DISTINCT a.name AS startNode, b.name AS endNode, r.rpn AS rpn, r.weightrt AS weightrt, r.weightgo AS weightgo") 
    
    // List<GetEdgeEntity> getAllEdges(@Param("nodeName") String nodeName);

}