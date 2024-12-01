/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author dangt
 */
public interface manager {

    void addWord(Word word);

    String search(String word, boolean isEnglishToVietnamese);

    void loadDictionary(String fileName) throws IOException;

    List<String> getEnglishWords();

    List<String> getVietnameseWords();
    HashMap<String, Word> getMapEnglishWords();
    HashMap<String, Word> getMapVietnameseWords();
}
