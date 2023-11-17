package com.example.demo.service;

import com.example.demo.dto.NodeDTO;
import com.example.demo.dto.NodeDTODelete;
import com.example.demo.model.entity.NodeEntity;
import com.example.demo.repository.NodeRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for managing nodes in a database.
 */
@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    /**
     * Creates a new node based on the provided NodeDTO.
     *
     * @param nodeDTO The NodeDTO object containing node data.
     * @return A NodeDTO representing the created node.
     */
    public NodeDTO createNode(NodeDTO nodeDTO) {
        NodeEntity nodeEntity = new NodeEntity();
        nodeEntity.setName(nodeDTO.getName());
        nodeEntity.setType(nodeDTO.getType());
        nodeEntity.setRpn(nodeDTO.getRpn());
        nodeEntity.setProperties(nodeDTO.getProperties());
        NodeEntity savedNode = nodeRepository.save(nodeEntity);
        return new NodeDTO(savedNode);
    }

    /**
     * Retrieves all nodes from the database and converts them to NodeDTO objects.
     *
     * @return A list of NodeDTO objects representing the nodes.
     */
    public List<NodeDTO> getNode(){
        List<NodeEntity> nodes = nodeRepository.findAllNodes();
       return nodes.stream().map(NodeDTO::new).collect(Collectors.toList());
    }
    
    /**
     * Updates an existing node based on its ID and the provided NodeDTO.
     *
     * @param id      The ID of the node to be updated.
     * @param nodeDTO The NodeDTO object containing updated node data.
     * @return A NodeDTO representing the updated node.
     */
    public NodeDTO updateNode(Long id, NodeDTO nodeDTO) {
        NodeEntity nodeEntity = nodeRepository.findById(id).orElseThrow();
        nodeEntity.setName(nodeDTO.getName());
        nodeEntity.setRpn(nodeDTO.getRpn());
        nodeEntity.setProperties(nodeDTO.getProperties());
        NodeEntity savedNode = nodeRepository.save(nodeEntity);
        return new NodeDTO(savedNode);
    }

    /**
     * Deletes nodes based on the provided NodeDTODelete object.
     *
     * @param nodeDelete The NodeDTODelete object containing node data to be deleted.
     * @return A list of NodeDTODelete objects representing the deleted nodes.
     */
    public List<NodeDTODelete> delete(NodeDTODelete nodeDelete){
        List<NodeEntity> nodeDeleted= nodeRepository.deleteNode(nodeDelete.getName(), nodeDelete.getRpn());
        return nodeDeleted.stream().map(NodeDTODelete::new).collect(Collectors.toList());
    }

    
    public List<NodeDTO> findNodesByRpn(String rpn) {
        List<NodeEntity> entities = nodeRepository.findByRpn(rpn);
        return entities.stream()
                       .map(NodeDTO::new)
                       .collect(Collectors.toList());
    }

}