package Lis;


import javax.servlet.*;
import java.io.IOException;

/*
@ClassName LoginFilter
@Description 登录过滤器
@author Enzo
@data 2018-8-7
*/
public class Filter implements javax.servlet.Filter {
        private  String charset = null;
    public void init(FilterConfig filterConfig) throws ServletException {
       /* charset=filterConfig.getInitParameter("charset");*/
    }
    /*
     * @Description 应用过滤器
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @author Enzo
     * @data 2018-8-7
     * */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*servletResponse.setCharacterEncoding("gbk");
        //强制转换
        HttpServletRequest Request=(HttpServletRequest)servletRequest;
        HttpServletResponse Response=(HttpServletResponse)servletResponse;
        HttpSession Session=Request.getSession();
        String username=(String)Session.getAttribute("username");
        String htmlUsername=servletRequest.getParameter("username");
        if(htmlUsername==null)
            return;
        if(htmlUsername.equals("")){
           Response.sendRedirect("/error_test.html");
        }*/
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
