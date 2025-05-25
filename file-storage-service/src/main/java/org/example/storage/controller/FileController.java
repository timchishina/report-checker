package org.example.storage.controller;


import org.example.storage.model.StoredFileInfo;
import org.example.storage.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileStorageService fileStorageService;

    @Autowired
    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public ResponseEntity<StoredFileInfo> uploadFile(@RequestParam("file") MultipartFile file) {
        StoredFileInfo savedFile = fileStorageService.saveFile(file);
        return ResponseEntity.ok(savedFile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") String fileId){
        return fileStorageService.loadFile(fileId);
    }

    @GetMapping
    public ResponseEntity<List<StoredFileInfo>> listFiles() {
        return ResponseEntity.ok(fileStorageService.listFiles());
    }
}
