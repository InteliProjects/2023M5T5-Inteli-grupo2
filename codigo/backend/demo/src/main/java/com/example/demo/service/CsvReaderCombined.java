package com.example.demo.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.example.demo.model.entity.NodeEntity;
import com.example.demo.dto.EdgeDTO;
import com.example.demo.dto.NodeDTO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service class reads data from CSV files and processes it to create nodes and edges.
 */
@Service
public class CsvReaderCombined {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private EdgeService edgeService;


    /**
     * Processes the CSV data for nodes.
     *
     * @param inputStream The input stream containing node data in CSV format.
     */
    public void processCsv(InputStream inputStream) {
        try (CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .withSkipLines(1) // Skip the first line (header)
                .build()) {

            ColumnPositionMappingStrategy<NodeEntity> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(NodeEntity.class);

            String[] columns = new String[]{"name", "type", "rpn"};
            strategy.setColumnMapping(columns);

            CsvToBean<NodeEntity> csvToBean = new CsvToBeanBuilder<NodeEntity>(csvReader)
                    .withMappingStrategy(strategy)
                    .withType(NodeEntity.class)
                    .build();

            List<NodeEntity> list = csvToBean.parse();

            Map<String, Object> propertiesMap = new HashMap<>();
            propertiesMap.put("local", "Norte");
            propertiesMap.put("sentido", "x");

            for (NodeEntity node : list) {
                NodeDTO nodeDTO = new NodeDTO();
                nodeDTO.setId(node.getId());
                nodeDTO.setName(node.getName());
                nodeDTO.setRpn(node.getRpn());
                nodeDTO.setType(node.getType());
                nodeDTO.setProperties(propertiesMap);

                nodeService.createNode(nodeDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes the CSV data for edges.
     *
     * @param inputStream The input stream containing edge data in CSV format.
     */
    public void processEdgeCsv(InputStream inputStream) {
        try (CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream))
            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
            .withSkipLines(1) // Skip the first line (header)
            .build()) {

        ColumnPositionMappingStrategy<EdgeDTO> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(EdgeDTO.class);

        String[] columns = new String[]{"startNode", "endNode", "rpn", "weightgo", "weightrt", "bidirecional"};
        strategy.setColumnMapping(columns);

        CsvToBean<EdgeDTO> csvToBean = new CsvToBeanBuilder<EdgeDTO>(csvReader)
                .withMappingStrategy(strategy)
                .withType(EdgeDTO.class)
                .build();

        List<EdgeDTO> list = csvToBean.parse();

        for (EdgeDTO edge : list) {
            edgeService.createEdge(edge);
        }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) throws Exception {
    }
}
