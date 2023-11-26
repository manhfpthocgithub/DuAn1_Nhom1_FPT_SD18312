/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import utils.JDBCHelper;
/**
 *
 * @author ADMIN
 */
public class DBConnect {
     static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dbUrl = "jdbc:sqlserver://localhost;port=1433;databaseName=DUAN1_NHOM1";
    static String user = "sa";
    static String password = "12345manh";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection(){
        try {
            Connection con = DriverManager.getConnection(dbUrl, user, password);
        return con ;    
        } catch (Exception e) {
            
            e.printStackTrace();
            return null ;
        }
        
    }
}
