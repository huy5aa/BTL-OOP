package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DictionaryManager {

    private HashMap<String, Word> englishToVietnamese; // HashMap cho từ tiếng Anh
    private HashMap<String, Word> vietnameseToEnglish; // HashMap cho từ tiếng Việt

    public DictionaryManager() {
        englishToVietnamese = new HashMap<>();
        vietnameseToEnglish = new HashMap<>();
    }

    public void addWord(Word word) {
        // Thêm từ vào cả hai HashMap
        englishToVietnamese.put(word.getEnglish().toLowerCase(), word);
        vietnameseToEnglish.put(word.getVietnamese().toLowerCase(), word);
    }

    public String search(String word, boolean isEnglishToVietnamese) {
        Word foundWord;
        if (isEnglishToVietnamese) {
            // Tìm từ tiếng Anh
            foundWord = englishToVietnamese.get(word.toLowerCase());
            if (foundWord != null) {
                return foundWord.getVietnamese() + " (" + foundWord.getType0fWord() + ")";
            }
        } else {
            // Tìm từ tiếng Việt
            foundWord = vietnameseToEnglish.get(word.toLowerCase());
            if (foundWord != null) {
                return foundWord.getEnglish() + " (" + foundWord.getType0fWord() + ")";
            }
        }
        return "Not found!";
    }

    public void loadDictionary(String fileName) throws IOException {
        List<Word> loadedWords = loadFromFile(fileName);
        for (Word word : loadedWords) {
            addWord(word); // Thêm từ vào HashMap khi tải từ điển
        }
    }

    // Trong DictionaryManager
    public List<String> getEnglishWords() {
        return new ArrayList<>(englishToVietnamese.keySet()); // Trả về danh sách từ tiếng Anh
    }

    public List<String> getVietnameseWords() {
        return new ArrayList<>(vietnameseToEnglish.keySet()); // Trả về danh sách từ tiếng Việt
    }

    private List<Word> loadFromFile(String fileName) {
        List<Word> words = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");

                // Trim các phần tử
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }

                // Tạo đối tượng Word từ các phần tử
                words.add(new Word(parts[0], parts[1], parts[2]));

            }
        } catch (FileNotFoundException e) {
            System.err.println("File không tìm thấy: " + e.getMessage());
        }

        return words;
    }

}
