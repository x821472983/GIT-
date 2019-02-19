package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    public  static Connection getCondition() {
        Connection conn = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获得数据库链接
            conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sky","root", "root");
            //
            System.err.println("打开数据库链接成功");
        } catch (SQLException e) {
            System.err.println("数据库链接异常");
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            System.err.println("驱动异常");
            e.printStackTrace();
        }
        return conn;
    }
}
