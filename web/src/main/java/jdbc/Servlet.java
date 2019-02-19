package jdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "Servlet",value = "/Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        try{
            addUser("ups","username,password","'"+username+"','"+password+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }


    }
    private int addUser (String tableName,String fields,String value) throws SQLException{
        //开数据库链接
        Connection coon=JDBC.getCondition();
        String sql ="insert into "+tableName+"("+fields+") values("+value+")";
        PreparedStatement ps = null;
        int result=0;
        try{
            ps =coon.prepareStatement(sql);
            result=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(ps!=null){
            ps.close();
        }
        if(coon!=null){
            coon.close();
        }
        return result;
    }
}
