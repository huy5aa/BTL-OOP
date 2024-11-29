package btvn_15_11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginForm extends JFrame implements ActionListener {

    private JLabel LoginLabel, UserLabel, PasswLabel;
    private JButton LoginButton, RegisterButton;
    private JTextField Username;
    private JPasswordField Password;

    public LoginForm() {

        setLayout(new GridLayout(4, 2, 20, 20));
        this.setSize(600, 300);


        LoginLabel = new JLabel("Login", JLabel.CENTER);
        this.add(LoginLabel);
        this.add(new JLabel()); 

        UserLabel = new JLabel("Username");
        Username = new JTextField(15);
        PasswLabel = new JLabel("Password");
        Password = new JPasswordField(15);
        RegisterButton = new JButton("Register");
        LoginButton = new JButton("Login");


        this.add(UserLabel);
        this.add(Username);
        this.add(PasswLabel);
        this.add(Password);
        this.add(RegisterButton);
        this.add(LoginButton);


        LoginButton.addActionListener(this);
        RegisterButton.addActionListener(this);


        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoginButton) {
            String username = this.Username.getText();
            String password = String.valueOf(this.Password.getPassword());

            if (checkLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Sai mật khẩu hoặc tên đăng nhập");
            }
        } else if (e.getSource() == RegisterButton) {
            RegisterForm register = new RegisterForm();
            register.setVisible(true);
        }
    }

    private boolean checkLogin(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/dangt"; 
        String user = "root";
        String pass = "Dang@211204"; 

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            return resultSet.next(); 
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to database.");
            return false;
        }
    }

    public static void main(String[] args) {
        // Tạo và hiển thị cửa sổ login
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}
