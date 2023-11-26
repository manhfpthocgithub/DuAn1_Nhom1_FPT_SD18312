package utils;
import java.sql.*;

public class JDBCHelper {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dbUrl = "jdbc:sqlserver://localhost;port=1433;databaseName=DUAN1_NHOM1";
    static String user = "sa";
    static String password = "123456";
    
    
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Lỗi");
            e.printStackTrace();
        }
    }
    
    
    public static PreparedStatement getStmt(String sql , Object ...args) throws Exception{
        Connection con = DriverManager.getConnection(dbUrl,user,password);
        PreparedStatement stmt ;
        if(sql.trim().startsWith("{")){
            stmt = con.prepareCall(sql);// proc
        }
        else{
            stmt = con.prepareStatement(sql);
        }
        
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i+1, args[i]);
        }
        return stmt ;
    }
    
    
    public static ResultSet executeQuery(String sql , Object ...args) throws Exception{
        PreparedStatement stmt = JDBCHelper.getStmt(sql, args);
        return stmt.executeQuery();
    }
    
    
    public static Object value(String sql , Object ...args) {
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null ;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public static int executeUpdate(String sql , Object ...args){
        try {
            PreparedStatement stmt = JDBCHelper.getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally{
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
