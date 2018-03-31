package org.jit.dcg.controller;

import com.alibaba.fastjson.JSONObject;
import org.jit.dcg.dto.UserDto;
import org.jit.dcg.service.UserService;
import org.jit.dcg.util.CryptographyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Resource
    private UserService userService;


    // 获得用户登录请求，判断用户名以及密码是否符合要求
    @RequestMapping(value = "/login_android.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login_android(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String username = request.getParameter("username");
        String pwd = CryptographyUtil.md5(request.getParameter("pwd"), "java1234");
        JSONObject logResultJson = new JSONObject();
        response.setContentType("application/json");
        if (username == null || pwd == null) {
            logResultJson.put("username",username);
            logResultJson.put("logResult", "用户名或者密码为空");
            logResultJson.put("logFlag", 0);
        } else if (!userService.searchrep(username)) {
            logResultJson.put("logResult", "用户名不存在");
            logResultJson.put("logFlag", 0);
        } else if (!userService.login(username, pwd)) {
            logResultJson.put("username",username);
            logResultJson.put("logResult", "密码错误");
            logResultJson.put("logFlag", 0);
        } else {
            // 验证通过，返回登录信息
            logResultJson.put("username",username);
            logResultJson.put("logResult", "登陆成功");
            logResultJson.put("logFlag", 1);
            logResultJson.put("username", username);
        }
        return logResultJson;
    }


    //用户注册功能
    @RequestMapping(value = "/register_android.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(HttpServletRequest request)   {
        // 1. 定义方法返回值
        JSONObject regJson = new JSONObject();
        String username = request.getParameter("username");
        String userpwd = request.getParameter("pwd");
        String userRoleId = request.getParameter("roleid");

        if (userpwd.length()<6){
            regJson.put("regResult", "密码过短");
            return regJson;
        }
        try {
            String pwd=CryptographyUtil.md5(userpwd, "java1234");
            UserDto user = new UserDto(null,username,pwd, null, "A", "A");
            System.out.println("用户信息为:"+user.toString());
            // 1.检查用户名不能为空
            // 2.密码不能为空
            if ( !(username.length()>0) || !(userpwd.length()>0)) {
                regJson.put("regResult", "用户名或密码为空");
                return regJson;
            }else if (userService.searchrep(username)) {
                regJson.put("regResult", "用户名已注册");
                return regJson;
            }else if (user.getName().length() < 6 ) {
                regJson.put("regResult", "用户名过短");
            }
            // 3.提示注册成功
            else {
                userService.register(user);
                regJson.put("username",username);
                regJson.put("regResult", "注册成功");
                return regJson;
            }
        } catch (Exception e) {
            e.printStackTrace();
            regJson.put("regResult", "注册异常");
            return regJson;
        }
        return regJson;
    }




}
