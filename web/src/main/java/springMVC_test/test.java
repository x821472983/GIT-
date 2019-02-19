package springMVC_test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {

    @ResponseBody
    @RequestMapping("/towObject")
    public BaseModel get(SysUser user,BaseModel baseModel){
        baseModel.setData(user);
        return baseModel;
        }
    @InitBinder("baseModel")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("baseModel.");
    }
}
