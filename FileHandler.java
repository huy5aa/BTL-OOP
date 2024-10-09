package dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<Word> loadFromFile(String fileName) throws IOException {
        List<Word> words = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 3) {
                words.add(new Word(parts[0], parts[1], parts[2]));
            }
        }
        reader.close();
        return words;
    }
}
