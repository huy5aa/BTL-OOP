package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DictionaryManager implements manager {

    private HashMap<String, Word> englishToVietnamese;
    private HashMap<String, Word> vietnameseToEnglish;

    public DictionaryManager() {
        englishToVietnamese = new HashMap<>();
        vietnameseToEnglish = new HashMap<>();
    }

    @Override
    public void addWord(Word word) {
        englishToVietnamese.put(word.getEnglish().toLowerCase(), word);
        vietnameseToEnglish.put(word.getVietnamese().toLowerCase(), word);
    }

    @Override
    public String search(String word, boolean isEnglishToVietnamese) {
        Word foundWord;
        if (isEnglishToVietnamese) {
            foundWord = englishToVietnamese.get(word.toLowerCase());
            if (foundWord != null) {
                return foundWord.getVietnamese() + " (" + foundWord.getType0fWord() + ")";
            }
        } else {
            foundWord = vietnameseToEnglish.get(word.toLowerCase());
            if (foundWord != null) {
                return foundWord.getEnglish() + " (" + foundWord.getType0fWord() + ")";
            }
        }
        return "Not found!";
    }

    @Override
    public void loadDictionary(String fileName) throws IOException {
        List<Word> loadedWords = loadFromFile(fileName);
        for (Word word : loadedWords) {
            addWord(word);
        }
    }

    @Override
    public List<String> getEnglishWords() {
        return new ArrayList<>(englishToVietnamese.keySet());
    }

    @Override
    public List<String> getVietnameseWords() {
        return new ArrayList<>(vietnameseToEnglish.keySet());
    }

    private List<Word> loadFromFile(String fileName) {
        List<Word> words = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                words.add(new Word(parts[0], parts[1], parts[2]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File không tìm thấy: " + e.getMessage());
        }
        return words;
    }

    @Override
    public HashMap<String, Word> getMapEnglishWords() {
        return englishToVietnamese;
    }

    @Override
    public HashMap<String, Word> getMapVietnameseWords() {
        return vietnameseToEnglish;
    }
}
