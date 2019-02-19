package springMVC_test;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
public class MVC_test2 {

    /*异常映射*/
    @RequestMapping(value = "/welcome", method = {RequestMethod.POST, RequestMethod.GET})//welcome要访问的url地址
    public String hello(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("hello,springmvc");
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new Exception("异常信息为: " + e);
        }

        return "MVC_test.jsp";
    }

    /*POJO映射*/
    @RequestMapping(value = "/sss",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView toUser(User user) throws Exception {
        System.out.print(user.getName()+"\n" + user.getAge()+ "\n"+user.getDate());
        ModelAndView mode=new ModelAndView();
        mode.setViewName("/datatime.jsp");
        mode.addObject("date",user.getDate());
        return mode;
    }

    /*AJAX映射*/
    @RequestMapping(value = "/getPerson")
    public void getPerson(String name, PrintWriter pw, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        pw.write("hello," + name);
    }

    /*输出格式化日期*/
    @RequestMapping("/date")
    public String date(Date date) {
        System.out.println(date);
        return "hello";
    }

    /*格式化日期*/
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        binder.registerCustomEditor(Date.class, new CustomDateEditor
                (new SimpleDateFormat("yyyy-MM-dd"),
                        true));
    }

    /*RESTFul风格的SringMVC*/
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Integer id) {
        System.out.println("get" + id);
        return "/hello";
    }

    /*在Controller中使用redirect方式处理请求*/
    @RequestMapping(value = "/redirect")
    public String redirect() {
        return "redirect:hello";
    }

    /*页面重定向*/
    @RequestMapping(value = "/hello")
    public String hello() {
        return "table.jsp";
    }

    /*使用@RequestParam注解指定参数的name，password*/
    @RequestMapping(value = "/param")
    public String testRequestParam(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "password") String password) {
        System.out.println(name + " " + password);
        return "/hello";
    }


    /*文件上传*/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") MultipartFile[] tempMFiles ) throws Exception {
        Calendar now=Calendar.getInstance();
        String datePath=now.get(Calendar.YEAR)+"年/"+
                (now.get(Calendar.MONTH)+1)+"月/"+
                now.get(Calendar.DATE)+"日/";
        for(MultipartFile file:tempMFiles)
        {
            if(file==null)
                continue;
            String fileName=file.getOriginalFilename();
            /*req.getSession().getServletContext().getRealPath("/") System.getProperty("webapp") 即为out文件夹的地址*/
            String path = System.getProperty("webapp") + "/upload/" +datePath+UUID.randomUUID() + fileName.substring(fileName.lastIndexOf('.'));
            File f = new File(path);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(file.getBytes());
            fos.flush();
            fos.close();
        }
        return "table2.jsp";
    }

    @ResponseBody
    @RequestMapping("/user")
    public User get(){
        User u=new User();
        u.setName("566");
        u.setAge("665");
        return u;
    }
    @RequestMapping("/show")
    public String showPerson(Map<String,Object> map){
        User p=new User();
        map.put("p",p);
        p.setName("张三");
        p.setAge("50");
        return "table.jsp";
    }
}
