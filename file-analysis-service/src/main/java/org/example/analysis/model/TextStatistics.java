package org.example.analysis.model;

public class TextStatistics {
    private int paragraphs;
    private int words;
    private int characters;

    public TextStatistics() {}

    public TextStatistics(int paragraphs, int words, int characters) {
        this.paragraphs = paragraphs;
        this.words = words;
        this.characters = characters;
    }

    public int getParagraphs() {
        return paragraphs;
    }

    public int getWords() {
        return words;
    }

    public int getCharacters() {
        return characters;
    }
}
