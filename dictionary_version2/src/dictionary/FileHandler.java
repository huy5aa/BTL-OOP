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
            for(int i=0;i<parts.length;i++){
               parts[i]=parts[i].trim();
                
            }
            if (parts.length == 3) {
                words.add(new Word(parts[0], parts[1], parts[2]));
            }
            else {
                words.add(new Word(parts[0], parts[1], ""));
            }
        }
        reader.close();
        return words;
    }
}
