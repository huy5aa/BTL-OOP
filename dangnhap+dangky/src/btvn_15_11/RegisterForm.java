package btvn_15_11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterForm extends JFrame implements ActionListener {

    private JTextField Firstname, Lastname, Username, mobile, email;
    private JPasswordField Password;
    private JButton LoginButton, RegisterButton;

    public RegisterForm() {

        setLayout(new GridLayout(3, 1, 20, 20));
        this.setSize(600, 600);


        JPanel p0 = new JPanel();
        p0.setLayout(new FlowLayout(FlowLayout.CENTER));  // Căn giữa nội dung
        p0.add(new JLabel("New User Register"));
        this.add(p0);


        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(6, 2, 10, 10));  // Sử dụng GridLayout cho các trường nhập
        p1.add(new JLabel("Firstname"));
        Firstname = new JTextField();
        p1.add(Firstname);

        p1.add(new JLabel("Lastname"));
        Lastname = new JTextField();
        p1.add(Lastname);

        p1.add(new JLabel("Username"));
        Username = new JTextField();
        p1.add(Username);

        p1.add(new JLabel("Password"));
        Password = new JPasswordField();
        p1.add(Password);

        p1.add(new JLabel("Email address"));
        email = new JTextField();
        p1.add(email);

        p1.add(new JLabel("Mobile address"));
        mobile = new JTextField();
        p1.add(mobile);

        this.add(p1);


        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        RegisterButton = new JButton("Register");
        LoginButton = new JButton("Login");
        p2.add(RegisterButton);
        p2.add(LoginButton);
        this.add(p2);


        RegisterButton.addActionListener(this);
        LoginButton.addActionListener(this);


        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == RegisterButton) {
            String firstname = Firstname.getText();
            String lastname = Lastname.getText();
            String username = Username.getText();
            String password = String.valueOf(Password.getPassword());
            String email = this.email.getText();
            String mobile = this.mobile.getText();

            if (checkUsernameExists(username)) {
                JOptionPane.showMessageDialog(this, "Username đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (registerUser(firstname, lastname, username, password, email, mobile)) {
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                    dispose();  // Close the register form after successful registration
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == LoginButton) {
            dispose();  // Close the register form without saving
        }
    }

    private boolean checkUsernameExists(String username) {
        String url = "jdbc:mysql://localhost:3306/dangt"; // Cập nhật URL của cơ sở dữ liệu
        String user = "root"; // Tên đăng nhập MySQL
        String pass = "Dang@211204"; // Mật khẩu MySQL

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Nếu có kết quả, tên đăng nhập đã tồn tại
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to database.");
            return false;
        }
    }

    private boolean registerUser(String firstname, String lastname, String username, String password, String email, String mobile) {
        String url = "jdbc:mysql://localhost:3306/dangt"; // Cập nhật URL của cơ sở dữ liệu
        String user = "root"; // Tên đăng nhập MySQL
        String pass = "Dang@211204"; // Mật khẩu MySQL

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "INSERT INTO users (firstname, lastname, username, password, email, mobilephone) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, mobile);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;  // Nếu có dòng bị ảnh hưởng, tức là đăng ký thành công
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to database.");
            return false;
        }
    }

    public static void main(String[] args) {
        new RegisterForm();
    }
}
