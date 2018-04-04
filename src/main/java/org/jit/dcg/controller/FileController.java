package org.jit.dcg.controller;

import com.alibaba.fastjson.JSONObject;
import org.jit.dcg.dto.FileDto;
import org.jit.dcg.service.FileService;
import org.jit.dcg.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FileController {

    @Resource
    FileService fileService;

    /**
     * 上传文件，支持多文件上传
     * @param file CommonsMultipartFile文件类
     * @param request  username
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fileUpload_android.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject revise1(@RequestParam("file") CommonsMultipartFile file[], HttpServletRequest request)
            throws Exception {
        JSONObject fileJson = new JSONObject();
        String username = request.getParameter("username");
        //构造文件上传的帮助类，实现文件上传功能
        FileUtil fileUtil = new FileUtil();
        List<Map<String, Object>> fileList = new ArrayList<>();
        fileList = fileUtil.fileUpLoad(file);
        if ((boolean)fileList.get(fileList.size()-1).get("upFileFlag")) {  //文件上传成功的状态
            List<FileDto> fileInfo = new ArrayList<>();
            for (int i = 0; i < fileList.size()-1; i++) {                  //集合中最后一个内容是文件上传标志，不是文件本身信息，所以要减1
                String fileOriginalName = fileList.get(i).get("fileOriginalName").toString();
                String fileNewName = fileList.get(i).get("fileNewName").toString();
                String fileTime = fileList.get(i).get("fileTime").toString();
                String fileUrl = fileList.get(i).get("fileUrl").toString();
                FileDto fileDto = new FileDto(fileNewName, username, fileUrl, fileTime, fileOriginalName,"A");
                fileService.upFile(fileDto);
                fileInfo.add(fileDto);
            }
            fileJson.put("upFileResult","文件上传成功");
            fileJson.put("fileInfo",fileInfo.toString());
        }else {                                                             //文件上传失败的状态
            fileJson.put("upFileResult","文件上传失败");
        }
        return fileJson;
    }


    /**
     * 按用户名和文件新名进行下载文件
     * @param response
     * @param request username/fileNewName
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/fileDownLoad_android.do")
    public JSONObject fileDownLoad(HttpServletResponse response, HttpServletRequest request) throws IOException {
        JSONObject downJson = new JSONObject();
        String username = request.getParameter("username");
        String fileNewName = request.getParameter("fileNewName");
        //List<FileDto> fileDtoList = fileService.selectByFileName(fileOriginalName,username);
        FileDto fileDto = fileService.selectByFileNewName(fileNewName,username,"A");
        if (fileDto != null){
            FileUtil fileUtil = new FileUtil();
            downJson = fileUtil.fileDownLoad(fileNewName,response);
            downJson.put("fileInfo",fileDto);
        }else {
            downJson.put("downResult","文件不存在");
        }
  /*      if (fileDtoList.size() > 0){
            FileDto fileDto = fileDtoList.get(0);
            String fileNewName = fileDto.getFilenewname();
            FileUtil fileUtil = new FileUtil();
            downJson = fileUtil.fileDownLoad(fileNewName,response);
            downJson.put("fileInfo",fileDto);
        }else {
            downJson.put("downResult","文件不存在");
        }*/
        return downJson;
    }


    /**
     * 查询用户文件列表
     * @param request username/
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryFileByPerson_android.do",method = RequestMethod.POST)
    public JSONObject queryFileByPerson(HttpServletRequest request){
        JSONObject fileJson = new JSONObject();
        String username = request.getParameter("username");
        List<FileDto> fileList = fileService.fileLitByPeron(username,"A");
        if (fileList.size() > 0){
            fileJson.put("fileList",fileList);
        } else {
            fileJson.put("fileList","文件列表为空。");
        }
        return fileJson;
    }


    /**
     * 文件删除：根据用户名和Ta传过来的文件新名称匹配，将文件的状态改为“X”
     * @param request username/fileNewName
     * @return
     */
    @RequestMapping(value = "/deleteFileByName_android.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject deleteFileByName(HttpServletRequest request){
        JSONObject delJson = new JSONObject();
        String username = request.getParameter("username");             //获取用户名
        String fileNewName = request.getParameter("fileNewName");       //获取需要删除的文件的新名称
        boolean delete = fileService.deleteFileByName(fileNewName,username,"X");
        if (delete){
            delJson.put("deleteResult","文件删除成功");
        }else {
            delJson.put("deleteResult","文件删除失败");
        }
        return delJson;
    }


    /**
     * 查询用户回收站文件
     * @param request username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fileRecycleBin_android.do",method = RequestMethod.POST)
    public JSONObject fileRecycleBin(HttpServletRequest request){
        JSONObject fileRecycleJson = new JSONObject();
        String username = request.getParameter("username");
        List<FileDto> fileList = fileService.fileLitByPeron(username,"X");
        if (fileList.size() > 0){
            fileRecycleJson.put("fileList",fileList);
        }else {
            fileRecycleJson.put("fileList","回收站文件列表为空!");
        }
        return fileRecycleJson;
    }

    /**
     * 恢复用户回收站中的文件
     * @param request username/fileNewName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fileRecover_android.do",method = RequestMethod.POST)
    public JSONObject recoverFile(HttpServletRequest request){
        JSONObject recoverJson = new JSONObject();
        String username = request.getParameter("username");
        String fileNewName = request.getParameter("fileNewName");
        List<FileDto> fileAll = fileService.queryPersonAll(fileNewName,username);
        if (fileAll.size() > 0){
            boolean recover = fileService.deleteFileByName(fileNewName,username,"A");
            if (recover){
                recoverJson.put("recoverResult","文件恢复成功");
            }else {
                recoverJson.put("recoverResult","文件恢复失败");
            }
        }else {
            recoverJson.put("recoverResult","需要恢复的文件不存在");
        }
        return recoverJson;
    }
}
