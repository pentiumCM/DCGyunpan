package org.jit.dcg.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.jit.dcg.dto.FileDto;
import org.jit.dcg.service.FileService;
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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
public class FileController {

    @Resource
    FileService fileService;

    @RequestMapping(value = "/fileUpload_android.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject revise1(@RequestParam("file") CommonsMultipartFile file[], HttpServletRequest request)
            throws Exception {
        String username = request.getParameter("username");
        JSONObject fileJson = new JSONObject();
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
     * 按用户名和文件名进行查找文件，若有多个同名文件，则取最新上传的文件
     * @param response
     * @param resquest
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/fileDownLoad_android.do")
    public JSONObject fileDownLoad(HttpServletResponse response, HttpServletRequest resquest) throws IOException {
        JSONObject downJson = new JSONObject();
        String username = resquest.getParameter("username");
        String fileOriginalName = resquest.getParameter("fileOriginalName");
        List<FileDto> fileDtoList = fileService.selectByFileName(fileOriginalName,username);
        if (fileDtoList.size() > 0){
            FileDto fileDto = fileDtoList.get(0);
            String fileNewName = fileDto.getFilenewname();
            FileUtil fileUtil = new FileUtil();
            downJson = fileUtil.fileDownLoad(fileNewName,response);
            downJson.put("fileInfo",fileDto);
        }else {
            downJson.put("downResult","文件不存在");
        }
        return downJson;
    }




}
