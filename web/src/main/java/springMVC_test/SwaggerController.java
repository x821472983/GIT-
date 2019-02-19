package springMVC_test;


import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import user.BaseModel;
import user.SysUser;


@Controller
@RequestMapping("/swagger")
public class SwaggerController {
    Logger log = Logger.getLogger(SwaggerController.class);


    @ResponseBody
    @RequestMapping(value = "/login")
    @ApiOperation(value = "用户登录", notes = "登录", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "user_name",
            value = "用户名", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码",
                    required = true, paramType = "query", dataType = "string")})
    public BaseModel login(SysUser user, BaseModel baseModel) throws Exception {
        log.info("登录的用户信息：" + user.toString());
        baseModel.setData(user);
        baseModel.setMessage("登陆成功");
        return baseModel;
    }
    /*批量处理*/
    @ResponseBody
    /*添加信息*/
    @RequestMapping(value = "/addUser")
    @ApiOperation(value = "添加用户", notes = "添加用户信息", httpMethod = "POST")
    public BaseModel add(@RequestBody SysUser user, BaseModel baseModel) throws Exception {
        log.info("新增的用户信息:" + user.toString());
        baseModel.setData(user);
        baseModel.setMessage("添加成功");
        return baseModel;
    }


    /*修改信息*/
    @ResponseBody
    @RequestMapping(value = "updateUser")
    @ApiOperation(value = "修改用户", notes = "根据用户id修改用户信息",
            httpMethod = "POST")
    public BaseModel update(@ModelAttribute SysUser user,BaseModel baseModel) throws Exception {
        log.info("修改的用户信息:" + user.toString());
        return baseModel;
    }

    @ResponseBody
    /*删除信息*/
    @RequestMapping(value = "deleteUser/{ids}")
    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户信息", httpMethod = "GET")
    public BaseModel delete(@PathVariable("ids") String ids, BaseModel baseModel) throws Exception {
        log.info("删除的用户信息：" + ids);
        baseModel.setData(ids);
        baseModel.setMessage("删除成功");
        return baseModel;
    }
    @ResponseBody
    @RequestMapping("/towObject")
    public springMVC_test.BaseModel get(springMVC_test.SysUser user, springMVC_test.BaseModel baseModel){
        baseModel.setData(user);
        return baseModel;
    }
    @InitBinder("baseModel")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("baseModel.");
    }

}




