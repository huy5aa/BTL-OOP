package dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuggestionEngine {
    private DictionaryManager dictionaryManager;

    // Constructor nhận vào một đối tượng DictionaryManager để truy cập dữ liệu từ điển
    public SuggestionEngine(DictionaryManager dictionaryManager) {
        this.dictionaryManager = dictionaryManager;
    }

    // Phương thức để lấy danh sách gợi ý từ dựa trên tiền tố (prefix)
    public List<String> getSuggestions(String prefix, boolean isEnglishToVietnamese) {
        List<String> suggestions = new ArrayList<>();
        prefix = prefix.toLowerCase(); // Chuyển tiền tố về chữ thường để tìm kiếm

        if (isEnglishToVietnamese) {
            // Duyệt qua tất cả các từ tiếng Anh và tìm từ có tiền tố khớp
            for (String word : dictionaryManager.getEnglishWords()) {
                if (word.startsWith(prefix)) {
                    suggestions.add(word);
                }
            }
        } else {
            // Duyệt qua tất cả các từ tiếng Việt và tìm từ có tiền tố khớp
            for (String word : dictionaryManager.getVietnameseWords()) {
                if (word.startsWith(prefix)) {
                    suggestions.add(word);
                }
            }
        }
        Collections.sort(suggestions);

        return suggestions; // Trả về danh sách gợi ý từ
    }
}
