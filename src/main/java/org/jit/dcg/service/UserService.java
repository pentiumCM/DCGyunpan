package org.jit.dcg.service;


import org.jit.dcg.dto.UserDto;

public interface UserService {

    public UserDto selectUserInfo(String name);

    public boolean searchrep(String name);

    public boolean login(String name,String pwd);

    public boolean register(UserDto user);

    public boolean updateHead(String username,String headImg);

    public boolean updatePwd(String username,String pwd);
}
