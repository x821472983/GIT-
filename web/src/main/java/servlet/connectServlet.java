package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "connectServlet", value = "/connect")
public class connectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("gbk");
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sky","root","root");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from list");

            List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();

            ResultSetMetaData metaData = rs.getMetaData();
            int col = metaData.getColumnCount();

            while(rs.next()) {
                Map<String,Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= col; ++i) {
                    String name = metaData.getColumnName(i).toLowerCase();
                    map.put(name, rs.getObject(i));
                    response.getWriter().append(rs.getObject(i).toString());
                }
                result.add(map);
                response.getWriter().append("\n");
            }
            response.getWriter().append(result.toString());
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }



        }catch (SQLException e) {
            response.getWriter().append(e.getMessage() +"11111");
        }catch (ClassNotFoundException e){
            response.getWriter().append(e.getMessage()+"22222");
        }
        try {
            if (conn != null) {
                conn.close();
            }
        }catch (Exception e) {
            response.getWriter().append(e.getMessage()+"33333");
        }

    }

}
