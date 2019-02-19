package springMVC_test;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MVC_test implements Controller {
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        /*return new ModelAndView("MVC_test.jsp","username","Fuck you");*/
        return new ModelAndView("MVC_test.jsp", "username", "Fuck you");
    }
}

/*
• @Controller：声明Action组件，负责注册bean到Spring上下文
        • @RequestMapping：用于为控制器指定可以处理的url请求
        • @RequestParam：用于指定参数的name属性
        • @RequestBody：用于读取Request请求的body部分数据
        • @ResponseBody：用于将控制器方法返回的对象写入到Response对象的body数据区
        • @PathVariable：用于指定url作为参数
        • @Resource用于注入，( 由j2ee提供 ) 默认按名称装配
        • @Autowired用于注入，(由spring提供) 默认按类型装配
        • @ExceptionHandler：用于异常处理的方法
        • @ControllerAdvice：用于使控制器成为全局的异常处理类
        • @ModelAttribute：用于优先调用被注解的方法，或注解参数中的隐藏对象*/
