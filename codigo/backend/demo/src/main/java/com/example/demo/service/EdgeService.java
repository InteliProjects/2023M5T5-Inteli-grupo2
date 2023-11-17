package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EdgeDTO;
import com.example.demo.dto.EdgeDTODelete;
import com.example.demo.model.entity.EdgeEntity;
import com.example.demo.model.entity.GetEdgeEntity;
import com.example.demo.model.entity.NodeEntity;
import com.example.demo.repository.EdgeRepository;
import com.example.demo.repository.GetEdgeRepository;

/**
 * Service class responsible for managing edges in a Neo4j graph database.
 */
@Service
public class EdgeService {

    @Autowired
    private EdgeRepository edgeRepository;

    @Autowired
    private GetEdgeRepository getEdgeRepository;

    @Autowired
    private Neo4jClient neo4jClient;

    public void setGetEdgeRepository(GetEdgeRepository getEdgeRepository) {
        this.getEdgeRepository = getEdgeRepository;
    }

    public void setEdgeRepository(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }
    /**
     * Creates a new edge based on the provided EdgeDTO.
     *
     * @param edgeDTO The EdgeDTO object containing edge data.
     * @return A message indicating the success of the edge creation.
     */
    public String createEdge(EdgeDTO edgeDTO) {
        try {
            EdgeEntity newEdge = new EdgeEntity();
            
            NodeEntity startNode = new NodeEntity();
            startNode.setName(edgeDTO.getStartNode());
            newEdge.setStartNode(startNode);
            NodeEntity endNode = new NodeEntity();
            endNode.setName(edgeDTO.getEndNode());
            newEdge.setEndNode(endNode);
            newEdge.setRpn(edgeDTO.getRpn());
            newEdge.setWeightgo(edgeDTO.getWeightgo());
            newEdge.setWeightrt(edgeDTO.getWeightrt());
            newEdge.setBidirecional(edgeDTO.isBidirecional());

            
            if (newEdge.isBidirecional() == true) {
                edgeRepository.createEdgeBiDic(newEdge.getStartNode().getName(), newEdge.getEndNode().getName(), newEdge.getWeightgo(), newEdge.getWeightrt(), newEdge.getRpn());
                return "Aresta Bidirecional criada";
            
             } else {
                edgeRepository.createEdgeDic(newEdge.getStartNode().getName(), newEdge.getEndNode().getName(), newEdge.getWeightgo(), newEdge.getRpn());
                return "Aresta Unidirecional criada";
            }
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar a aresta, erro: " + e.getMessage() + "");
        }
        }

       
    // public List<GetEdgeEntity> getAllEdges() {
    //     return edgeRepository.getAllEdges(); // Suponha que você tenha um método findAll no EdgeRepository
    // }

    /**
     * Executes a Neo4j Cypher query to retrieve edge data.
     *
     * @return A list of maps containing edge data.
     */
    @Query("MATCH (a:NodeEntity)-[r:TUBULAÇÃO]->(b:NodeEntity) RETURN a.name AS startNode, b.name AS endNode, r.rpn, r.weightgo, r.weightrt")
    public List<Map<String, Object>> executeNeo4jQuery() {
        return (List<Map<String, Object>>) neo4jClient.query("MATCH (a:NodeEntity)-[r:TUBULAÇÃO]->(b:NodeEntity) RETURN a.name AS startNode, b.name AS endNode, r.rpn, r.weightgo, r.weightrt")
            .fetch().all();
    }

    public List<Map<String, Object>> executeNeo4jQueryRpn(String rpn) {
        String query = String.format(
            "MATCH (a:NodeEntity)-[r:TUBULAÇÃO]->(b:NodeEntity) WHERE r.rpn = '%s' RETURN a.name AS startNode, b.name AS endNode, r.rpn, r.weightgo, r.weightrt",
            rpn
        );
        return (List<Map<String, Object>>) neo4jClient.query(query).fetch().all();
    }

    /**
     * Retrieves all edges connected to a specific start node.
     *
     * @param startNode The name of the start node.
     * @return A list of GetEdgeEntity objects representing the edges.
     */
    public List<GetEdgeEntity> getAllEdgesByStartNode(String startNode) {
        return getEdgeRepository.getAllEdgesByStartNode(startNode);
    }

    /**
     * Deletes an edge based on the provided EdgeDTODelete object.
     *
     * @param edgeDTODelete The EdgeDTODelete object containing edge data to be deleted.
     * @return A message indicating the success of the edge deletion.
     */
    public String deleteEdge(EdgeDTODelete edgeDTODelete){
        try {
            EdgeEntity newEdge = new EdgeEntity();
            
            NodeEntity startNode = new NodeEntity();
            startNode.setName(edgeDTODelete.getStartNode());
            newEdge.setStartNode(startNode);
            NodeEntity endNode = new NodeEntity();
            endNode.setName(edgeDTODelete.getEndNode());
            newEdge.setEndNode(endNode);
            newEdge.setRpn(edgeDTODelete.getRpn());
            
            edgeRepository.deleteEdge(newEdge.getStartNode().getName(), newEdge.getEndNode().getName(), newEdge.getRpn());
            return "Aresta deletada";
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar a aresta, erro: " + e.getMessage() + "");
        }
    }

}