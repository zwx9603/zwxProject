package com.soft.demo.mapper;

import com.soft.demo.entity.StudentEntity;
import com.soft.demo.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    UserEntity querUserInfoByInfo(String userName, String passdWord);
    /*
    * 查询用户的信息
    * */
    List<UserEntity> querUserInfo();

    /*
    * 查询学生类的信息
    * */
    List<StudentEntity> queryStudentInfo();
}
