package com.example.demo.controller;

import com.example.demo.repository.GetEdgeRepository;
import com.example.demo.repository.NodeAlgRepository;
import com.example.demo.service.AlgorithmService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller class responsible for handling algorithms and calculations related to graph traversal.
 */
@RestController
@RequestMapping("/algorithm")
@CrossOrigin(origins = "http://localhost:5174")
public class AlgorithmController {

    /** The service responsible for performing graph algorithms. */
    @Autowired
    private AlgorithmService algorithmService;

    /** The repository for NodeAlg entities. */
    @Autowired
    private NodeAlgRepository nodeRepository;

    /** The repository for GetEdgeEntity entities. */
    @Autowired
    private GetEdgeRepository getEdgeRepository;

    /**
     * Calculates the shortest path between two nodes in the graph.
     *
     * @param startNode The name of the starting node.
     * @param endNode The name of the destination node.
     * @return A list of node names representing the shortest path between the given nodes.
     */
    @GetMapping("/calculate")
    public List<String> calculateShortestPath(
            @RequestParam("startNode") String startNode,
            @RequestParam("endNode") String endNode) {
        List<String> shortestPath = algorithmService.findShortestPath(startNode, endNode);
        return shortestPath;
    }

    /**
     * Downloads the shortest path between two nodes in Excel format.
     *
     * @param startNode The name of the starting node.
     * @param endNode The name of the destination node.
     * @param filePath The file path for saving the Excel file.
     * @return A ResponseEntity containing the Excel file as bytes for download.
     */
    @GetMapping("/downloadExcel")
    public ResponseEntity<byte[]> downloadExcel(
            @RequestParam("startNode") String startNode,
            @RequestParam("endNode") String endNode,
            @RequestParam("filePath") String filePath) {

        XSSFWorkbook workbook = algorithmService.getShortestPathAsExcel(startNode, endNode, filePath);

        if (workbook != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "shortest_path.xlsx");

            // Convert XSSFWorkbook to bytes for HTTP response
            byte[] workbookBytes;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                workbook.write(baos);
                workbookBytes = baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build(); // Handle errors appropriately
            }

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(workbookBytes.length)
                    .body(workbookBytes);
        } else {
            return ResponseEntity.notFound().build(); // Handle the case of file generation failure
        }
    }
}
