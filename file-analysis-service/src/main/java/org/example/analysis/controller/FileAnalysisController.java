package org.example.analysis.controller;

import org.example.analysis.model.TextStatistics;
import org.example.analysis.service.TextAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyze")
public class FileAnalysisController {

    private final TextAnalysisService service;

    @Autowired
    public FileAnalysisController(TextAnalysisService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public TextStatistics analyze(@PathVariable("id") String id) {
        return service.analyzeFile(id);
    }
}
