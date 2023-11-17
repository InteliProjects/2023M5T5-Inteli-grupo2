package com.example.demo.controller;

import com.example.demo.dto.NodeDTO;
import com.example.demo.dto.NodeDTODelete;
import com.example.demo.model.entity.NodeEntity;
import com.example.demo.service.NodeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller class that handles HTTP requests related to node operations.
 */
@RestController
@RequestMapping("/nodes")
@CrossOrigin(origins = "http://localhost:5174")
public class NodeController {

    /** The service responsible for node-related operations. */
    @Autowired
    private NodeService nodeService;

    /**
     * Retrieves a list of all nodes.
     *
     * @return A list of NodeDTO objects representing all nodes.
     */
    @GetMapping()
     public List<NodeDTO> getNode(){       
        return nodeService.getNode();
    }

    @GetMapping("/find")
    public List<NodeDTO> findNodes(@RequestParam String rpn) {
        return nodeService.findNodesByRpn(rpn);
    }

    /**
     * Creates a new node based on the provided node DTO.
     *
     * @param nodeDTO The node DTO containing node information.
     * @return A ResponseEntity containing the created node DTO.
     */
    @PostMapping
    public ResponseEntity<NodeDTO> createNode(@RequestBody NodeDTO nodeDTO) {
        NodeDTO createdNode = nodeService.createNode(nodeDTO);
        return ResponseEntity.ok(createdNode);
    }

    /**
     * Updates an existing node with the provided node DTO.
     *
     * @param id      The ID of the node to update.
     * @param nodeDTO The node DTO containing updated node information.
     * @return A ResponseEntity containing the updated node DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<NodeDTO> updateNode(@PathVariable Long id, @RequestBody NodeDTO nodeDTO) {
        NodeDTO updatedNode = nodeService.updateNode(id, nodeDTO);
        return ResponseEntity.ok(updatedNode);
    }

    /**
     * Deletes one or more nodes based on the provided node DTOs.
     *
     * @param nodeDelete The list of NodeDTODelete objects specifying nodes to delete.
     * @return A ResponseEntity containing a list of deleted NodeDTODelete objects.
     */
    @DeleteMapping
    public ResponseEntity<List<NodeDTODelete>> deleteNode(@RequestBody NodeDTODelete nodeDelete){
        List<NodeDTODelete> deleteNode= nodeService.delete(nodeDelete);
        return ResponseEntity.ok(deleteNode);
    }
}