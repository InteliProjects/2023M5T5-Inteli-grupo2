package com.example.demo.service;

import com.example.demo.model.entity.NodeEntityAlg;
import com.example.demo.model.entity.EdgeEntityAlg;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class responsible for implementing graph algorithms(Djikstra).
 */
@Service
public class AlgorithmService {

    @Autowired EdgeService edgeService;

    private Map<String, NodeEntityAlg> nodes = new HashMap<>();

    /**
     * Builds a graph by fetching data from the database using EdgeService.
     */
    public void buildGraph() {
        List<Map<String, Object>> edgesData = edgeService.executeNeo4jQuery();

        for (Map<String, Object> edgeData : edgesData) {
            String startNodeName = (String) edgeData.get("startNode");
            String endNodeName = (String) edgeData.get("endNode");
            double weight = edgeData.get("r.weightgo") != null ? (double) edgeData.get("r.weightgo") : (double) edgeData.get("r.weightrt");

            NodeEntityAlg startNode = nodes.computeIfAbsent(startNodeName, NodeEntityAlg::new);
            NodeEntityAlg endNode = nodes.computeIfAbsent(endNodeName, NodeEntityAlg::new);

            EdgeEntityAlg edge = new EdgeEntityAlg();
            edge.setStartNode(startNode);
            edge.setEndNode(endNode);
            edge.setWeightgo(weight);

            startNode.getConnections().add(edge);
        }
    }

    /**
     * Finds the shortest path between two nodes in the graph using Dijkstra's algorithm.
     *
     * @param startNodeName The name of the starting node.
     * @param endNodeName   The name of the ending node.
     * @return A list of node names representing the shortest path.
     */
    public List<String> findShortestPath(String startNodeName, String endNodeName) {
        buildGraph();

        NodeEntityAlg startNode = nodes.get(startNodeName);
        NodeEntityAlg endNode = nodes.get(endNodeName);

        Map<NodeEntityAlg, Double> distances = new HashMap<>();
        Map<NodeEntityAlg, NodeEntityAlg> previousNodes = new HashMap<>();

        distances.put(startNode, 0.0);
        PriorityQueue<NodeEntityAlg> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        queue.add(startNode);

        while (!queue.isEmpty()) {
            NodeEntityAlg currentNode = queue.poll();

            for (EdgeEntityAlg edge : currentNode.getConnections()) {
                NodeEntityAlg neighbor = edge.getEndNode();
                double newDistance = distances.get(currentNode) + edge.getWeightgo();

                if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousNodes.put(neighbor, currentNode);

                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (NodeEntityAlg node = endNode; node != null; node = previousNodes.get(node)) {
            path.add(0, node.getName());
        }

        return path;
    }

    /**
     * Gets the nodes in the graph.
     *
     * @return A map of node names to NodeEntityAlg objects.
     */
    public Map<String, NodeEntityAlg> getNodes() {
        return nodes;
    }

    /**
     * Generates an Excel workbook representing the shortest path between two nodes in the graph.
     *
     * @param startNodeName The name of the starting node.
     * @param endNodeName   The name of the ending node.
     * @param filePath      The path where the Excel file will be saved.
     * @return The generated XSSFWorkbook.
     */
    public XSSFWorkbook getShortestPathAsExcel(String startNodeName, String endNodeName, String filePath) {
        buildGraph();

        // Calculate the shortest path
        List<String> shortestPath = findShortestPath(startNodeName, endNodeName);

        // Find nodes adjacent to nodes in the shortest path
        HashSet<String> adjacentNodes = new HashSet<>();
        for (String nodeName : shortestPath) {
            HashSet<String> neighbors = findNeighbors(nodeName); // Replace findNeighbors with the actual method to find adjacent nodes
            adjacentNodes.addAll(neighbors);
        }

        // Create a new Excel workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("ShortestPath");

        // Cell B2
        XSSFRow cellB2Row = sheet.createRow(1);
        cellB2Row.setHeightInPoints(30);
        XSSFCell cellB2 = cellB2Row.createCell(1);
        cellB2.setCellValue("Caminho " + startNodeName + " até " + endNodeName);

        // Cell A2 (Header "#")
        XSSFCell headerCell = cellB2Row.createCell(0);
        headerCell.setCellValue("#");

        // Set style for the whole row (row 2)
        XSSFCellStyle greyRowStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10); // Font size 10 (adjust as needed)
        greyRowStyle.setFont(headerFont);
        greyRowStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        greyRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        greyRowStyle.setAlignment(HorizontalAlignment.CENTER);
        greyRowStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Apply the style to the whole row
        for (int i = 0; i <= 1; i++) {
            XSSFCell cell = cellB2Row.getCell(i);
            cell.setCellStyle(greyRowStyle);
        }

        // Fill cells with nodes in the shortest path in the second row
        XSSFRow nodeRow = sheet.createRow(0);
        nodeRow.setHeightInPoints(78);
        int columnIndex = 2; // Start from column C

        // Split nodes into nodes in the shortest path and neighbors
        HashSet<String> nodesInPath = new HashSet<>();
        HashSet<String> neighborsOfNodesInPath = new HashSet<>();
        boolean[] isInShortestPath = new boolean[shortestPath.size()]; // Initially all set to false

        for (String nodeName : shortestPath) {
            if (adjacentNodes.contains(nodeName)) {
                nodesInPath.add(nodeName);
                int index = shortestPath.indexOf(nodeName);
                isInShortestPath[index] = true; // Mark nodes in the shortest path
            }
        }

        for (String nodeName : adjacentNodes) {
            if (!nodesInPath.contains(nodeName)) {
                neighborsOfNodesInPath.add(nodeName);
            }
        }

        // Set to track nodes already added to cells
        HashSet<String> nodesAdded = new HashSet<>();

        // Iterate over nodes in the shortest path and their neighbors
        for (int i = 0; i < shortestPath.size(); i++) {
            String nodeName = shortestPath.get(i);

            // Check if the node is neither the start node nor the end node
            if (!nodeName.equals(startNodeName) && !nodeName.equals(endNodeName)) {
                // Check if the node has not been added to this column already
                if (!nodesAdded.contains(nodeName)) {
                    int rowIndex = 1; // Row 2
                    // Fill the current cell
                    XSSFCell currentCell = nodeRow.createCell(columnIndex);
                    currentCell.setCellValue(nodeName);
                    XSSFCellStyle currentCellStyle = workbook.createCellStyle();

                    if (adjacentNodes.contains(nodeName)) {
                        // Node belongs to the shortest path (green)
                        currentCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                    } else {
                        // Node does not belong to the shortest path (red)
                        currentCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                    }

                    currentCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                    // Fill the cell below
                    int columnIndexForNextRow = columnIndex;
                    XSSFRow rowForNextPath = sheet.getRow(rowIndex);

                    if (rowForNextPath == null) {
                        rowForNextPath = sheet.createRow(rowIndex);
                    }

                    XSSFCell cellForNextRow = rowForNextPath.createCell(columnIndexForNextRow);
                    cellForNextRow.setCellValue("O");
                    // Set font for the white "O"
                    XSSFFont font = workbook.createFont();
                    font.setColor(IndexedColors.WHITE.getIndex()); // White font color
                    font.setFontHeightInPoints((short) 12); // Font size 12
                    currentCellStyle.setFont(font);

                    // Center-align text in the cell
                    currentCellStyle.setAlignment(HorizontalAlignment.CENTER);
                    currentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    applyCellStyle(cellForNextRow, currentCellStyle);

                    columnIndex++;

                    // Add the node to the set of nodes already added
                    nodesAdded.add(nodeName);

                    // Add neighbors in the next column and fill cells below in red
                    HashSet<String> neighbors = findNeighbors(nodeName); // Replace findNeighbors with the actual method to find adjacent nodes

                    HashSet<String> nodesVisitedCopy = new HashSet<>(nodesInPath);

                    // Remove common nodes from the neighbors list
                    neighbors.removeAll(nodesVisitedCopy);

                    for (String neighbor : neighbors) {
                        if (!nodeName.equals(startNodeName) && !nodeName.equals(endNodeName)) {
                            if (!nodesAdded.contains(neighbor)) {
                                XSSFCell neighborCell = nodeRow.createCell(columnIndex);
                                neighborCell.setCellValue(neighbor);

                                // Fill the neighbor cell in red
                                XSSFCellStyle neighborCellStyle = workbook.createCellStyle();
                                neighborCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                                neighborCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                                // Fill the cell below the neighbor in red
                                int columnIndexForNeighborNextRow = columnIndex;
                                XSSFRow rowForNeighborNextPath = sheet.getRow(rowIndex);

                                if (rowForNeighborNextPath == null) {
                                    rowForNeighborNextPath = sheet.createRow(rowIndex);
                                }

                                XSSFCell cellForNeighborNextRow = rowForNeighborNextPath.createCell(columnIndexForNeighborNextRow);
                                cellForNeighborNextRow.setCellValue("X");
                                XSSFFont neighborFont = workbook.createFont();
                                neighborFont.setColor(IndexedColors.WHITE.getIndex()); // White font color
                                neighborFont.setFontHeightInPoints((short) 12); // Font size 12
                                neighborCellStyle.setFont(neighborFont);
                                // Center-align text in the cell
                                neighborCellStyle.setAlignment(HorizontalAlignment.CENTER);
                                neighborCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

                                applyCellStyle(cellForNeighborNextRow, neighborCellStyle);

                                columnIndex++;
                            }
                        }
                    }
                }
            }
        }

        // Automatically adjust column width to fit content
        for (int i = 0; i <= columnIndex; i++) {
            sheet.autoSizeColumn(i);
        }

        // Save the workbook to a file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
            return workbook; // Return the generated workbook
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle errors appropriately in your application
        }
    }

    /**
     * Applies the given cell style to a cell.
     *
     * @param cell       The cell to which the style will be applied.
     * @param cellStyle  The cell style to apply.
     */
    private void applyCellStyle(Cell cell, CellStyle cellStyle) {
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Vertically center-align text
        cellStyle.setWrapText(true); // Enable text wrapping to display vertically
        cellStyle.setBorderBottom(BorderStyle.DOUBLE);
        cellStyle.setBorderTop(BorderStyle.DOUBLE);
        cellStyle.setBorderLeft(BorderStyle.DOUBLE);
        cellStyle.setBorderRight(BorderStyle.DOUBLE);
        cell.setCellStyle(cellStyle);
    }

    /**
     * Finds neighbors of a given node in the graph.
     *
     * @param nodeName The name of the node for which neighbors will be found.
     * @return A set of node names representing neighbors.
     */
    public HashSet<String> findNeighbors(String nodeName) {
        NodeEntityAlg node = nodes.get(nodeName);
        List<EdgeEntityAlg> connections = node.getConnections();

        // Use a HashSet to track neighboring nodes and avoid duplicates
        HashSet<String> neighborSet = new HashSet<>();

        // Iterate over connections and add neighboring nodes to the HashSet
        for (EdgeEntityAlg connection : connections) {
            String neighborName = connection.getEndNode().getName();
            neighborSet.add(neighborName);
        }

        return neighborSet;
    }
}
