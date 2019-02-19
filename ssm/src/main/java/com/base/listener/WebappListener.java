package com.base.listener;

import com.base.constants.ConstantsBase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class WebappListener  implements ServletContextListener {
    public WebappListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        String webAppRootKey = sce.getServletContext().getRealPath(File.separator);
        System.setProperty(ConstantsBase.WEBAPP_ROOT, webAppRootKey + File.separator);
        File file = new File(webAppRootKey);
        System.setProperty(ConstantsBase.PROJECT_NAME, file.getName());
        System.out.println(System.getProperty("projectName"));
        System.setProperty(ConstantsBase.WEBAPP_CLASSES, webAppRootKey + File.separator
                + "WEB-INF" + File.separator + "classes" + File.separator);
    }
    public void contextDestroyed(ServletContextEvent arg0) {}
}
