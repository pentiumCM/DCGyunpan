package org.jit.dcg.service;


import org.jit.dcg.dto.UserDto;

public interface UserService {

    public boolean searchrep(String name);

    public boolean login(String name,String pwd);

    public boolean register(UserDto user);
}
