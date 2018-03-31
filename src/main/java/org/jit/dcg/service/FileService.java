package org.jit.dcg.service;

import org.jit.dcg.dto.FileDto;

import java.util.List;

public interface FileService {

    //查找某一用户的所有文件信息
    List<FileDto> fileLitByPeron(String username);

    //上传文件信息
    boolean upFile(FileDto fileDto);

    //按文件名查找匹配文件
    List<FileDto> selectByFileName(String fileOriginalName,String fileOwner);

    //删除文件名某一个文件(逻辑删除)
    boolean deleteFileByName(String fileOriginalName,String fileOwner);

}
