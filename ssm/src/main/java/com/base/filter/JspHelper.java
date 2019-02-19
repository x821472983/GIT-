package com.base.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JspHelper implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRes = (HttpServletRequest) servletRequest;

        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String path = httpRes.getRequestURI();
        if (path != null && path.indexOf(".jsp") > 0) {
            String peojectName = httpRes.getContextPath();
            String projectUrl = servletRequest.getScheme() + "://" + servletRequest.getServerName() + ":" + servletRequest.getServerPort() + peojectName + "/";
            String basePath = "</base href=\"" + projectUrl + "\"/>";
            basePath += "<meta charset=\"UTF-8\">\n";
            basePath += "<script type=\"text/javascript\" src=\"/base/js/jquery-3.3.1.js\"></script>\n";
            basePath += "<script type=\"application/javascript\" src=\"/base/js/security.js\"></script>\n";
            basePath += "<script type=\"application/javascript\" src=\"/base/js/jquery.cookie.js\"></script>";
            basePath += "<link rel=\"stylesheet\" type=\"text/css\" href=\"/base/plugins/layui-v2.3.0/layui/css/layui.css\">\n";
            basePath += "<script type=\"text/javascript\" src=\"/base/plugins/layui-v2.3.0/layui/layui.js\"></script>";

            servletRequest.setAttribute("basePath", basePath);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }


}
