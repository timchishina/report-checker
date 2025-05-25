package org.example.storage.service;

import org.example.storage.model.StoredFileInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileStorageService {

    private final Path storageDir = Paths.get("uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(storageDir);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать директорию хранения", e);
        }
    }

    public StoredFileInfo saveFile(MultipartFile file) {
        try {
            String id = UUID.randomUUID().toString();
            String fileName = id + ".txt";
            Path target = storageDir.resolve(fileName);
            Files.write(target, file.getBytes());

            return new StoredFileInfo(id, file.getOriginalFilename(), fileName);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла", e);
        }
    }

    public ResponseEntity<byte[]> loadFile(String id) {
        try {
            Path file = storageDir.resolve(id + ".txt");
            byte[] content = Files.readAllBytes(file);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".txt\"")
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(content);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public List<StoredFileInfo> listFiles() {
        try {
            return Files.list(storageDir)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .map(path -> {
                        String fileName = path.getFileName().toString();
                        String id = fileName.replace(".txt", "");
                        return new StoredFileInfo(id, null, fileName);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении директории", e);
        }
    }
}
