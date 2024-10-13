package gui;

import dictionary.DictionaryManager;
import dictionary.History;
import dictionary.SuggestionEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

public class DictionaryGUI extends JFrame {

    private DictionaryManager manager;
    private History history;
    private SuggestionEngine suggestionEngine; // Thêm SuggestionEngine
    private JTextField searchField;
    private JTextArea resultArea;
    private JButton searchButton;
    private JButton historyButton;
    private JComboBox<String> languageSelection;
    private JList<String> suggestionList; // Thêm JList để hiển thị gợi ý
    private DefaultListModel<String> suggestionListModel; // Model cho JList

    public DictionaryGUI() {
        manager = new DictionaryManager();
        suggestionEngine = new SuggestionEngine(manager); // Khởi tạo SuggestionEngine
        history = new History();
        setTitle("Dictionary");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        searchField = new JTextField(20);
        inputPanel.add(searchField, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(inputPanel, gbc);

        // Thiết lập danh sách gợi ý
        suggestionListModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionListModel);
        suggestionList.setVisibleRowCount(5); // Hiển thị 5 hàng gợi ý cùng lúc
        suggestionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane suggestionScrollPane = new JScrollPane(suggestionList);
        suggestionScrollPane.setPreferredSize(new Dimension(200, 100)); // Kích thước scroll

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(suggestionScrollPane, gbc);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(resultPanel, gbc);

        searchButton = new JButton("Search");
        historyButton = new JButton("History");

        String[] options = {"English to Vietnamese", "Vietnamese to English"};
        languageSelection = new JComboBox<>(options);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 3));
        optionsPanel.add(languageSelection);
        optionsPanel.add(searchButton);
        optionsPanel.add(historyButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(optionsPanel, gbc);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchWord();
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHistory();
            }
        });

        // Xử lý sự kiện nhập liệu và gợi ý
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String prefix = searchField.getText().trim();
                boolean isEnglishToVietnamese = languageSelection.getSelectedIndex() == 0;
                updateSuggestions(prefix, isEnglishToVietnamese);
            }
        });
        
        // Xử lý sự kiện chọn gợi ý
        suggestionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && suggestionList.getSelectedValue() != null) {
                searchField.setText(suggestionList.getSelectedValue());
                searchWord();
                //resultArea.setText(manager.search(suggestionList.getSelectedValue(),languageSelection.getSelectedIndex() == 0));
            }
        });

        setContentPane(mainPanel);
    }
    
    private void updateSuggestions(String prefix, boolean isEnglishToVietnamese) {
        suggestionListModel.clear();
        List<String> suggestions = suggestionEngine.getSuggestions(prefix, isEnglishToVietnamese);
        for (String suggestion : suggestions) {
            suggestionListModel.addElement(suggestion);
        }
    }

    private void searchWord() {
        String word = searchField.getText().trim();
        boolean isEnglishToVietnamese = languageSelection.getSelectedIndex() == 0;
        String result = manager.search(word, isEnglishToVietnamese);
        if (!word.isEmpty()) {
            history.addHistory(word + " : " + result);
        }
        resultArea.setText(result);
    }

    private void showHistory() {
        JDialog historyDialog = new JDialog(this, "Search History", true);
        historyDialog.setSize(300, 400);

        LinkedList<String> historyList = history.getHistoryList();
        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);

        for (String word : historyList) {
            historyArea.append(word + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyDialog.add(scrollPane);

        historyDialog.setVisible(true);
    }

    public static void main(String[] args) {
        DictionaryGUI gui = new DictionaryGUI();
        gui.setVisible(true);

        try {
            gui.manager.loadDictionary("dictionary.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
