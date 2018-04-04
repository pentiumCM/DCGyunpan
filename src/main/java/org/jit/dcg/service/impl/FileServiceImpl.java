package org.jit.dcg.service.impl;

import org.jit.dcg.dto.FileDto;
import org.jit.dcg.mapper.FileDtoMapper;
import org.jit.dcg.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    FileDtoMapper fileDtoMapper;


    @Override
    public List<FileDto> queryPersonAll(String fileNewName, String fileOwner) {
        return fileDtoMapper.queryPersonAll(fileNewName,fileOwner);
    }

    @Override
    public List<FileDto> fileLitByPeron(String username,String fileStatus) {
        return fileDtoMapper.selectByPerson(username,fileStatus);
    }

    @Override
    public boolean upFile(FileDto fileDto) {
        int n = fileDtoMapper.insert(fileDto);
        if (n > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<FileDto> selectByFileOriginalName(String fileOriginalName, String fileOwner,String fileStatus) {
        return fileDtoMapper.selectByFileOriginalName(fileOriginalName,fileOwner,fileStatus);
    }

    @Override
    public FileDto selectByFileNewName(String fileNewName, String fileOwner, String fileStatus) {
        return fileDtoMapper.selectByFileNewName(fileNewName,fileOwner,fileStatus);
    }

    @Override
    public boolean deleteFileByName(String fileNewName, String fileOwner,String fileStatus) {
        int n = fileDtoMapper.deleteByFileName(fileNewName,fileOwner,fileStatus);
        if (n > 0){
            return true;
        }
        return false;
    }


}
