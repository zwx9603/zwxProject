package com.soft.demo.service;

import com.soft.demo.bean.StudentBean;
import com.soft.demo.bean.UserBean;
import com.soft.demo.entity.UserEntity;

import java.util.List;

public interface UserService {


    UserEntity queryUserInfo(String userName,String passdWord);

    /*
    * 对用户信息进行缓存
    * */
    List<UserBean> queryUserInfo();

    /**
    * @Description: 从缓存中取User数据
    *
    * @param: id
    * @return:
    */
    UserBean queryInfoByCache(String id);


    /*
     * 查询学生类的信息,并放入缓存
     * */
    List<StudentBean> queryStudentInfo();


    /**
     * @Description: 从缓存中取Student数据
     *
     * @param: id
     * @return:
     */
    StudentBean queryStuInfoByCache(String sno);



    String testInfo();

    /*
    * 测试environment调用配置类
    * */
    String testEnvironment();
}
