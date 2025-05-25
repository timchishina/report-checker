package org.example.storage.service;

import org.example.storage.model.StoredFileInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileStorageServiceTest {

    private FileStorageService service;

    @BeforeEach
    void setUp() {
        service = new FileStorageService();
    }

    @Test
    void testSaveAndListFiles() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.txt", "text/plain", "Пример текста".getBytes()
        );
        StoredFileInfo info = service.saveFile(file);

        assertNotNull(info.getId());
        assertEquals("test.txt", info.getOriginalName());

        List<StoredFileInfo> files = service.listFiles();
        assertTrue(files.stream().anyMatch(f -> f.getId().equals(info.getId())));

        Path filePath = Path.of("uploads", info.getStoredName());
        assertTrue(Files.exists(filePath));

        Files.deleteIfExists(filePath);
    }
}
