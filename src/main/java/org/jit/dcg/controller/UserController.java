package org.jit.dcg.controller;

import com.alibaba.fastjson.JSONObject;
import org.jit.dcg.dto.FileDto;
import org.jit.dcg.dto.UserDto;
import org.jit.dcg.service.FileService;
import org.jit.dcg.service.UserService;
import org.jit.dcg.util.CryptographyUtil;
import org.jit.dcg.util.FileUtil;
import org.jit.dcg.util.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private FileService fileService;


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

    /**
     * 用户修改密码
     * @param request username/oldpwd/newpwd
     * @return
     */
    @RequestMapping(value = "/updatePwd_andriod.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updataPwd(HttpServletRequest request){
        JSONObject regJson = new JSONObject();
        String username = request.getParameter("username");
        String oldpwd =request.getParameter("oldpwd");
        //将用户传过来的新密码加密
        String newpwd = CryptographyUtil.md5(request.getParameter("newpwd"),"java1234");
        String pwd = CryptographyUtil.md5(oldpwd, "java1234");
        if(userService.login(username,pwd)){
            if (userService.updatePwd(username,newpwd)) {
                regJson.put("regResult", "修改成功");
            }else {
                regJson.put("regResult","修改失败");
            }
        }
        else{
            regJson.put("regResult", "密码错误");
        }
        return regJson;
    }


    /**
     * 用户修改头像功能
     * @param request
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateHeadImage_android.do", method = RequestMethod.POST)
    public JSONObject updataImage(HttpServletRequest request,@RequestParam("headImg") CommonsMultipartFile file[]){
        JSONObject headImgJson = new JSONObject();
        String username = request.getParameter("username");
        //构造文件上传的帮助类，实现文件上传功能
        FileUtil fileUtil = new FileUtil();
        List<Map<String, Object>> fileList = new ArrayList<>();
        fileList = fileUtil.fileUpLoad(file);
        if ((boolean)fileList.get(fileList.size()-1).get("upFileFlag")) {  //文件上传成功的状态
            List<FileDto> fileInfo = new ArrayList<>();
            for (int i = 0; i < fileList.size()-1; i++) {                  //集合中最后一个内容是文件上传标志，不是文件本身信息，所以要减1
                String headOriginalName = fileList.get(i).get("fileOriginalName").toString();
                String headNewName = fileList.get(i).get("fileNewName").toString();
                String headTime = fileList.get(i).get("fileTime").toString();
                String headUrl = fileList.get(i).get("fileUrl").toString();
                FileDto fileDto = new FileDto(headNewName, username, headUrl, headTime, headOriginalName,"H");
                fileService.upFile(fileDto);
                userService.updateHead(username,headNewName);
                fileInfo.add(fileDto);
            }
            headImgJson.put("updateHeadResult","头像修改成功");
            headImgJson.put("fileInfo",fileInfo.toString());
        }else {                                                             //文件上传失败的状态
            headImgJson.put("updateHeadResult","头像修改失败");
        }
        return headImgJson;
    }

    /**
     * 下载用户头像功能
     * @param request username 用户名
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/downHeadImg_android.do", method = RequestMethod.GET)
    public JSONObject showHead(HttpServletRequest request,HttpServletResponse response){
        JSONObject downJson = new JSONObject();
        String username = request.getParameter("username");
        UserDto userDto = userService.selectUserInfo(username);
        String headName = userDto.getImagepath();
        FileDto fileDto = fileService.selectByFileNewName(headName,username,"H");
        if (fileDto != null){
            FileUtil fileUtil = new FileUtil();
            //进行下载头像
            downJson = fileUtil.fileDownLoad(headName,response);
            downJson.put("fileInfo",fileDto);
        }else {
            downJson.put("fileInfo","头像信息不存在");
        }
        return downJson;
    }



}
