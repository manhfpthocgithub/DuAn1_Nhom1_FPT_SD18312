/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class ConnectDAO {
    private final String serverName = "HOA-MUSICIAN";
    private final String dbName = "DUAN1_NHOM1";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String pass = "05092004";
    String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";user=" + userID + ";password=" + pass;
    public Connection getConnectDAO() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("ban da cai dirver jdbc");
        } catch (Exception e) {
            System.out.println("ban chua cai dat driver JDBC");
        }
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("ket noi thanh cong");
            return conn;
        } catch (Exception e) {
            System.out.println("ket noi voi co so du lieu khong thanh cong");
        }

        return null;
}
    public static void main(String[] args) {
        ConnectDAO con = new ConnectDAO();
        con.getConnectDAO();
    }
}
