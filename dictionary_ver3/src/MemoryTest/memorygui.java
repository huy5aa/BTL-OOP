package MemoryTest;

import gui.DictionaryGUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class memorygui extends JFrame implements ActionListener {

    private JLabel currentWordLabel;
    private JButton checkAnswerButton;
    private JButton nextQuestionButton;
    private JTextField userAnswerField;
    public MemoryTestManager memoryTestManager;
    private int numberOfQuestions;
    private boolean isEnglishToVietnameseTest;
    private String currentWord;
    private List<String> wordList;
    public static int currentQuestionIndex;

    public memorygui(int numberOfQuestions, boolean isEnglishToVietnameseTest) {
        this.numberOfQuestions = numberOfQuestions;
        this.isEnglishToVietnameseTest = isEnglishToVietnameseTest;
        this.memoryTestManager = new MemoryTestManager();
        this.currentQuestionIndex = 0;

        // Cấu hình cửa sổ JFrame
        setTitle("Test Memory");
        setSize(500, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Tạo các thành phần giao diện
        JPanel currenwordpanel = new JPanel();
        currenwordpanel.setLayout(new BorderLayout());
        currentWordLabel = new JLabel();
        currenwordpanel.add(currentWordLabel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(currenwordpanel, gbc);

        JPanel userpanel = new JPanel();
        userpanel.setLayout(new BorderLayout());
        userAnswerField = new JTextField(15);
        userpanel.add(userAnswerField, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(userpanel, gbc);

        JPanel checkpanel = new JPanel();
        checkpanel.setLayout(new BorderLayout());
        checkAnswerButton = new JButton("CheckQuestion");
        checkpanel.add(checkAnswerButton, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(checkpanel, gbc);

        JPanel nextpanel = new JPanel();
        nextpanel.setLayout(new BorderLayout());
        nextQuestionButton = new JButton("NextQuestion");
        nextQuestionButton.setEnabled(false);
        nextpanel.add(nextQuestionButton, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nextpanel, gbc);
        // Thêm các thành phần vào JFrame

        // Gán các sự kiện hành động
        checkAnswerButton.addActionListener(this);
        nextQuestionButton.addActionListener(this);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {

                dispose();
                DictionaryGUI gui = new DictionaryGUI();
                gui.setVisible(true);

                try {
                    gui.manager.loadDictionary("dictionary.txt");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    public void setWordList(List<String> wordList) {
        this.wordList = new ArrayList<>();
        Random rand = new Random();
        List<String> tempWordList = new ArrayList<>(wordList);
        for (int i = 0; i < numberOfQuestions; i++) {
            if (tempWordList.isEmpty()) {
                break;
            }
            int randomIndex = rand.nextInt(tempWordList.size());
            this.wordList.add(tempWordList.get(randomIndex));
            tempWordList.remove(randomIndex);
        }
    }

    public void startTest() {

        if (isEnglishToVietnameseTest) {
            this.setWordList(memoryTestManager.getEnglishWords());
            currentWord = wordList.get(currentQuestionIndex);
            currentWordLabel.setText(currentWord);
        } else {
            this.setWordList(memoryTestManager.getVietnameseWords());
            currentWord = wordList.get(currentQuestionIndex);
            currentWordLabel.setText(currentWord);
        }
    }

    private void loadNextWord() {
        currentQuestionIndex++;
        if (currentQuestionIndex < wordList.size()) {
            currentWord = wordList.get(currentQuestionIndex);
            currentWordLabel.setText(currentWord);
            userAnswerField.setText("");
            nextQuestionButton.setEnabled(false); // Disable next button until answer is checked
        } else {
            JOptionPane.showMessageDialog(this, "Đã hoàn thành tất cả các câu hỏi!");
            int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn làm lại bài kiểm tra?", "Kết thúc", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                currentQuestionIndex = 0;
                faceFrame facee = new faceFrame();
                facee.setVisible(true);
            } else {
                dispose();
                DictionaryGUI gui = new DictionaryGUI();
                gui.setVisible(true);

                try {
                    gui.manager.loadDictionary("dictionary.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == checkAnswerButton) {
            // Lấy câu trả lời từ người dùng
            String userAnswer = userAnswerField.getText().trim();

            // Kiểm tra câu trả lời
            String correctAnswer = memoryTestManager.search(currentWord, isEnglishToVietnameseTest);
            if (userAnswer.equals(correctAnswer)) {
                JOptionPane.showMessageDialog(this, "Đúng rồi!");
            } else {
                JOptionPane.showMessageDialog(this, "Sai! Đáp án đúng: " + correctAnswer);
            }

            // Kích hoạt nút tiếp theo
            nextQuestionButton.setEnabled(true);
        } else if (event.getSource() == nextQuestionButton) {
            // Tải từ mới
            loadNextWord();
        }
    }

}
