1. Xác định yêu cầu
Yêu cầu chức năng:
Thêm từ mới vào từ điển.
Tìm kiếm từ trong từ điển bằng tiếng Anh hoặc tiếng Việt.
Hiển thị thông tin từ bao gồm nghĩa và loại từ.
Tải từ điển từ tệp văn bản.
Giao diện người dùng đơn giản cho việc tìm kiếm.
Yêu cầu phi chức năng:
Hiệu suất tìm kiếm phải nhanh (độ phức tạp O(1)).
Giao diện người dùng thân thiện và dễ sử dụng.
Xử lý lỗi khi không tìm thấy từ hoặc gặp sự cố khi tải tệp.
2. Phân tích thiết kế ra lớp
Các lớp cần thiết:
Word: Lớp đại diện cho một từ trong từ điển.
FileHandler: Lớp xử lý việc đọc và ghi tệp.
DictionaryManager: Lớp quản lý từ điển và các thao tác liên quan đến từ.
DictionaryGUI: Lớp tạo giao diện người dùng.(
3. Xây dựng biểu đồ lớp (UML)
Dưới đây là mô tả về biểu đồ lớp UML cho dự án từ điển:
Lớp Word:
Thuộc tính:
english: String
vietnamese: String
partOfSpeech: String
Phương thức:
getEnglish(): String
getVietnamese(): String
getPartOfSpeech(): String
Lớp FileHandler:
Phương thức:
loadFromFile(fileName: String): List<Word>
Lớp DictionaryManager:
Thuộc tính:
englishToVietnamese: HashMap<String, Word>
vietnameseToEnglish: HashMap<String, Word>
Phương thức:
addWord(word: Word): void
search(word: String, isEnglishToVietnamese: boolean): String
loadDictionary(fileName: String): void
Lớp DictionaryGUI: 
