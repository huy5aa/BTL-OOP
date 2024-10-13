package dictionary;

public class Word {
    private String english;
    private String vietnamese;
    private String type0fWord; // các dạng của từ:Noun, Verb, etc.

    public Word(String english, String vietnamese, String partOfSpeech) {
        this.english = english;
        this.vietnamese = vietnamese;
        this.type0fWord = partOfSpeech;
    }

    public String getEnglish() {
        return english;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public String getType0fWord() {
        return type0fWord;
    }

  

    
}
