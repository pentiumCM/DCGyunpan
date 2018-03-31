package org.jit.dcg.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.dcg.dto.FileDto;

import java.util.List;

public interface FileDtoMapper {

    //按文件名查找文件
    List<FileDto> selectByFileName(@Param("fileOriginalName") String fileOriginalName,@Param("fileOwner") String fileOwner);

    // 按用户名查找所有的文件
    List<FileDto> selectByPerson(String fileOwner);

    // 按文件名删除文件
    int deleteByFileName(@Param("fileOriginalName") String fileOriginalName,@Param("fileOwner") String fileOwner);

    // 插入文件信息
    int insert(FileDto fileDto);

}