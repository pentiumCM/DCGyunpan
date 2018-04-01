package org.jit.dcg.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.dcg.dto.FileDto;

import java.util.List;

public interface FileDtoMapper {

    //按文件名(原名)查找文件
    List<FileDto> selectByFileOriginalName(@Param("fileOriginalName") String fileNewName,@Param("fileOwner") String fileOwner,@Param("fileStatus") String fileStatus);

    //按文件名(新名称)查找文件
    FileDto selectByFileNewName(@Param("fileNewName") String fileNewName,@Param("fileOwner") String fileOwner,@Param("fileStatus") String fileStatus);

    // 按用户名查找所有的文件
    List<FileDto> selectByPerson(@Param("fileOwner") String fileOwner, @Param("fileStatus") String fileStatus);

    // 按文件名(新的名称)删除文件
    int deleteByFileName(@Param("fileNewName") String fileNewName,@Param("fileOwner") String fileOwner,@Param("fileStatus") String fileStatus);

    // 插入文件信息
    int insert(FileDto fileDto);

}