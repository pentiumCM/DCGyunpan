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
    public List<FileDto> fileLitByPeron(String username) {
        return fileDtoMapper.selectByPerson(username);
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
    public List<FileDto> selectByFileName(String fileOriginalName, String fileOwner) {
        return fileDtoMapper.selectByFileName(fileOriginalName,fileOwner);
    }

    @Override
    public boolean deleteFileByName(String fileOriginalName, String fileOwner) {
        int n = fileDtoMapper.deleteByFileName(fileOriginalName,fileOwner);
        if (n > 0){
            return true;
        }
        return false;
    }

}
