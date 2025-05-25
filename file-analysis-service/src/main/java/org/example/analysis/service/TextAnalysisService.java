package org.example.analysis.service;

import org.example.analysis.model.TextStatistics;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TextAnalysisService {

    private final String storageServiceUrl = "http://localhost:8081/files/";

    public TextStatistics analyzeFile(String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(storageServiceUrl + id, String.class);

        String text = response.getBody();


        return analyzeText(text);
    }
    public TextStatistics analyzeText(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new TextStatistics(0, 0, 0);
        }

        int paragraphCount = text.split("\\n\\s*").length;
        int wordCount = text.trim().split("\\s+").length;
        int charCount = text.length();

        return new TextStatistics(paragraphCount, wordCount, charCount);
    }

}
