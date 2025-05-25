package org.example.storage.model;

public class StoredFileInfo {
    private String id;
    private String originalName;
    private String storedName;

    public StoredFileInfo(String id, String originalName, String storedName) {
        this.id = id;
        this.originalName = originalName;
        this.storedName = storedName;
    }

    public String getId() {
        return id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getStoredName() {
        return storedName;
    }
}
