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


}
