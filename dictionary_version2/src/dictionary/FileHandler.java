package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public static List<Word> loadFromFile(String fileName) {
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
