package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.service.CsvReaderCombined;

/**
 * A controller class responsible for handling CSV file upload and processing operations.
 */
@RestController
@RequestMapping("/csv")
public class CsvController {

    /** The service for combined CSV processing. */
    @Autowired
    private final CsvReaderCombined csvReaderCombined;

    /**
     * Constructor for CsvController.
     *
     * @param csvReaderCombined The CsvReaderCombined service for CSV processing.
     */
    public CsvController(CsvReaderCombined csvReaderCombined) {
        this.csvReaderCombined = csvReaderCombined;
    }

    /**
     * Handles the upload and processing of a CSV file.
     *
     * @param file The multipart file containing the CSV data.
     * @return A ResponseEntity with a status message indicating the success or failure of the CSV processing.
     */
    @PostMapping()
    public ResponseEntity<String> uploadCsv(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo CSV está vazio.");
        }

        try {
            csvReaderCombined.processCsv(file.getInputStream());
            return ResponseEntity.ok("Arquivo CSV foi processado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar o arquivo CSV.");
        }
    }

    /**
     * Handles the upload and processing of a CSV file for creating edges.
     *
     * @param file The multipart file containing the CSV data for edge creation.
     * @return A ResponseEntity with a status message indicating the success or failure of the edge CSV processing.
     */
    @PostMapping("/edges")
    public ResponseEntity<String> uploadCsvForEdges(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo CSV está vazio.");
        }

        try {
            csvReaderCombined.processEdgeCsv(file.getInputStream());
            return ResponseEntity.ok("Arquivo CSV para criação de arestas foi processado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar o arquivo CSV para criação de arestas.");
        }
    }
}