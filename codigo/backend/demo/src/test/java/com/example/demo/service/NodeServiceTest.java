package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.NodeDTO;
import com.example.demo.dto.NodeDTODelete;
import com.example.demo.model.entity.NodeEntity;
import com.example.demo.repository.NodeRepository;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class NodeServiceTest {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private NodeRepository nodeRepository;

    @Test
    public void testCreateNode() {
        NodeDTO nodeDTO = new NodeDTO();
        nodeDTO.setName("TestNode");
        nodeDTO.setType("TestType");
        nodeDTO.setRpn("TestRpn");

        NodeDTO createdNode = nodeService.createNode(nodeDTO);

        assertNotNull(createdNode);
        assertNotNull(createdNode.getId());
        assertEquals("TestNode", createdNode.getName());
        assertEquals("TestType", createdNode.getType());
        assertEquals("TestRpn", createdNode.getRpn());
    }

    @Test
    public void testGetNode() {
        List<NodeDTO> nodes = nodeService.getNode();

        assertNotNull(nodes);
        assertTrue(nodes.size() > 0);
    }

    @Test
    public void testUpdateNode() {
        NodeDTO nodeDTO = new NodeDTO();
        nodeDTO.setName("TestNode");
        nodeDTO.setType("TestType");
        nodeDTO.setRpn("TestRpn");

        NodeDTO createdNode = nodeService.createNode(nodeDTO);

        createdNode.setName("UpdatedName");
        createdNode.setRpn("UpdatedRpn");

        NodeDTO updatedNode = nodeService.updateNode(createdNode.getId(), createdNode);

        assertNotNull(updatedNode);
        assertEquals("UpdatedName", updatedNode.getName());
        assertEquals("UpdatedRpn", updatedNode.getRpn());
    }

    @Test
    public void testDeleteNode() {
        NodeDTO nodeDTO = new NodeDTO();
        nodeDTO.setName("TestNode");
        nodeDTO.setType("TestType");
        nodeDTO.setRpn("TestRpn");

        NodeDTO createdNode = nodeService.createNode(nodeDTO);

        NodeDTODelete nodeDelete = new NodeDTODelete();
        nodeDelete.setName(createdNode.getName());
        nodeDelete.setRpn(createdNode.getRpn());

        nodeService.delete(nodeDelete);

        Optional<NodeEntity> deletedNode = nodeRepository.findById(createdNode.getId());
        
        assertFalse(deletedNode.isPresent());
    }
}
