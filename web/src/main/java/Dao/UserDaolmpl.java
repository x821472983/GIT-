/*
package Dao;

import jdbc.JDBC;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserDaolmpl implements UserDao {

    public String getPassword(String username){
        String password=null;
        Connection coon =null;
        try{
            coon= JDBC.getCondition();
            Statement statement=coon.createStatement();
            String sql="select password form uersdata where username='"+username+"'";
            ResultSet rs=statement.executeQuery(sql);
            if(rs.next()){
                password=rs.getString(1);
            }
            if(rs!=null){
                rs.close();
            }
            if(statement!=null){
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(coon!=null)
                    coon.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return password;
    }
}
*/
