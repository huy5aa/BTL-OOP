package dictionary;

public class Word {
    private String english;
    private String vietnamese;
    private String partOfSpeech; // Noun, Verb, etc.

    public Word(String english, String vietnamese, String partOfSpeech) {
        this.english = english;
        this.vietnamese = vietnamese;
        this.partOfSpeech = partOfSpeech;
    }

    public String getEnglish() {
        return english;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }
}
