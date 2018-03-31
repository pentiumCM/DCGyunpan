package org.jit.dcg.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件帮助类
 */
public class FileUtil {


    public List<Map<String, Object>> fileUpLoad(CommonsMultipartFile file[]) {
        PropertiesUtil pUtil = new PropertiesUtil();
        List<Map<String, Object>> fList = new ArrayList<>();
        try {
            //获取系统时间作为文件上传的时间
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(day));
            String fileTime = df.format(day).toString();
            Properties properties = pUtil.readProperties("jdbc.properties");
            String filePath = properties.getProperty("filepath");
            for (int i = 0; i < file.length; i++) {
                String fileName = file[i].getOriginalFilename();
                String newFileName = getNewName(fileName);
                File dir = new File(filePath, newFileName);
                if (!dir.exists()) {
                    System.out.println("文件不存在，准备创建！");
                    dir.mkdir();
                }
                try {
                    file[i].transferTo(dir);
                    Map<String, Object> map = new HashMap<>();
                    map.put("fileOriginalName",fileName);           //文件的原来名称
                    map.put("fileNewName", newFileName);    //文件上传到服务器新的名字
                    map.put("fileTime",fileTime);                   //文件上传的时间
                    map.put("fileUrl",filePath);                    //文件上传到服务器的地址
                    fList.add(map);
                } catch (IllegalStateException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Map<String,Object> upFileMap = new HashMap();
            upFileMap.put("upFileFlag",true);
            fList.add(upFileMap);

        }catch (Exception e){
            e.printStackTrace();
            Map<String,Object> upFileMap = new HashMap();
            upFileMap.put("upFileFlag",false);
            fList.add(upFileMap);
        }

        return fList;
    }

    /**
     * 文件下载功能
     * @param fileName  文件在服务器上的新名称
     * @param response
     */
    public JSONObject fileDownLoad(String fileName, HttpServletResponse response) {
        JSONObject downJson = new JSONObject();
        PropertiesUtil pUtil = new PropertiesUtil();
        try {
            Properties properties = pUtil.readProperties("jdbc.properties");
            String path = properties.getProperty("filepath");
            OutputStream oStream = response.getOutputStream();
            String filePath = path + File.separator + fileName;
            File file = new File(filePath);
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream; charset=utf-8");
            oStream.write(FileUtils.readFileToByteArray(file));
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            downJson.put("downResult","下载失败");
        } catch (Exception e) {
            e.printStackTrace();
            downJson.put("downResult","下载成功");
        }
        return downJson;
    }

    /**
     * 获得新名称
     * @param name
     * @return
     */
    public String getNewName(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newName = sdf.format(new Date()) + "_" +name;
        return newName;
    }

}



