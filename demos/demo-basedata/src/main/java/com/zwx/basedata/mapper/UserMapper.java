package com.zwx.basedata.mapper;

import com.zwx.basedata.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    UserEntity querUserInfoByInfo(String userName, String passdWord);

    /*
    * 查询用户的信息
    * */
    List<UserEntity> querUserInfo();

}
