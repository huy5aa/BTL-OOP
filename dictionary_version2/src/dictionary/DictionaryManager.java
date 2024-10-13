package dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                return foundWord.getVietnamese() + " (" + foundWord.getPartOfSpeech() + ")";
            }
        } else {
            // Tìm từ tiếng Việt
            foundWord = vietnameseToEnglish.get(word.toLowerCase());
            if (foundWord != null) {
                return foundWord.getEnglish() + " (" + foundWord.getPartOfSpeech() + ")";
            }
        }
        return "Not found!";
    }

    public void loadDictionary(String fileName) throws IOException {
        List<Word> loadedWords = FileHandler.loadFromFile(fileName);
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

}
