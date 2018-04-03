package org.jit.dcg.service.impl;

import org.jit.dcg.dto.UserDto;
import org.jit.dcg.mapper.UserDtoMapper;
import org.jit.dcg.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDtoMapper userDtoMapper;


    //查询用户信息
    @Override
    public UserDto selectUserInfo(String name) {
        return userDtoMapper.selectByUserName(name);
    }

    @Override
    public boolean searchrep(String name) {
        UserDto u = userDtoMapper.selectByUserName(name);
        if(u == null){
            return false;
        }
        else
            return true;
    }

    @Override
    public boolean login(String username,String password) {
        UserDto u = userDtoMapper.selectByUserName(username);
        String pp = u.getPwd();
        if(pp.equals(password))
        {
            return true;
        }
        else
            return false;
    }


    @Override
    public boolean register(UserDto user) {
        userDtoMapper.insert(user);
        return false;
    }

    @Override
    public boolean updateHead(String username, String headImg) {
        int n = userDtoMapper.updateHeadImg(username,headImg);
        if (n > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePwd(String username,String newpwd){
        int n = userDtoMapper.updatePwd(username,newpwd);
        if(n > 0){
            return true;
        }else {
            return false;
        }
    }

}
