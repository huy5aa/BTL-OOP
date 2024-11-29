/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btvn_15_11;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class account {
    private static final String URL = "jdbc:mysql://localhost:3306/dangt"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "Dang@211204"; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kết nối thất bại!");
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Kết nối đến cơ sở dữ liệu thành công.");
        } else {
            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
        }
    }
}
