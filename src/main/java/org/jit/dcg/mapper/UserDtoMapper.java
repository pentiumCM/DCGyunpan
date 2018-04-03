package org.jit.dcg.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.dcg.dto.UserDto;

public interface UserDtoMapper {

    int insert(UserDto record);

    int insertSelective(UserDto record);

    UserDto  selectByUserName(String name);

    int updateByPrimaryKey(UserDto record);

    public boolean register(UserDto user);

    public int updateHeadImg(@Param("name")String username, @Param("imagepath") String head);


}