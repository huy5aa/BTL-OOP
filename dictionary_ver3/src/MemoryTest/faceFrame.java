package MemoryTest;

import gui.DictionaryGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dangt
 */
public class faceFrame extends JFrame implements ActionListener {

    private JButton startButton;
    private JTextField inputJtext;
    private JLabel messageLabel;
    private JComboBox<String> languageSelection;

    public faceFrame() {
        // Cấu hình JFrame
        setTitle("Memory Test");
        setSize(400, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Khởi tạo các thành phần giao diện
        messageLabel = new JLabel("Số lượng câu muốn test "+ "("+" 1 đến 100"+" )");
        inputJtext = new JTextField(10);
        startButton = new JButton("Kiểm tra");
        String[] options = {"English to Vietnamese", "Vietnamese to English"};
        languageSelection = new JComboBox<>(options);

        // Thêm các thành phần vào JFrame
        add(messageLabel);
        add(inputJtext);
        add(startButton);
        add(languageSelection);
        startButton.addActionListener(this);
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

    private void handleInput() throws IOException {
        try {
            int number = Integer.parseInt(inputJtext.getText());
            if (number < 1 || number > 100) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số trong khoảng cho phép.");
                return;
            }

            boolean isEnglishToVietnamese = languageSelection.getSelectedIndex() == 0;
            memorygui memoryGUI = new memorygui(number, isEnglishToVietnamese);
            memoryGUI.memoryTestManager.loadDictionary("dictionary.txt");
            memoryGUI.setVisible(true);
            memoryGUI.startTest();
            this.dispose();
            

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nhập lại");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            try {
                handleInput();
            } catch (IOException ex) {
                Logger.getLogger(faceFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public static void main(String[] args) {
        faceFrame neww = new faceFrame();
        neww.setVisible(true);
    }

}
