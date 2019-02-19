package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("gbk");
        String username = getInitParameter("username");
        String password = getInitParameter("password");
        if (username != null && password != null) {
            if (username.equals("admin")) {
                if (password.equals("admin")) {


                } else {
                    System.out.println("密码错误");
                }
            } else {
                System.out.println("用户名错误");
            }
        } else {
            System.out.println("用户名密码不能为空");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
