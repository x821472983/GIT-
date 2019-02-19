package Lis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Listener implements ServletContextListener, HttpSessionAttributeListener,HttpSessionListener {
    public static int count = 0;
    public static Map<String,String> map=new HashMap<String, String>();
    public void contextInitialized(ServletContextEvent sce) {
        String webAppRootKey = sce.getServletContext().getRealPath(File.separator);
        File file = new File(webAppRootKey);
        System.setProperty("webapp", webAppRootKey);
        System.setProperty("project_name", file.getName());
        System.setProperty("classes", webAppRootKey + File.separator + "WEB-INF" + File.separator + "classes" + File.separator);


    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        map.put(httpSessionBindingEvent.getName(),httpSessionBindingEvent.getValue().toString());
        count++;
    }

    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        map.remove(httpSessionEvent.getSession().getId());
        count--;
    }
}
