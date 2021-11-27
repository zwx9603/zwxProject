package com.zwx.basedata.service.impl;

import com.zwx.basedata.entity.UserEntity;
import com.zwx.basedata.mapper.UserMapper;
import com.zwx.basedata.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
/*@PropertySource(value = {"classpath:config/data.properties",
        "classpath:application-dev.yml",
        "classpath:config/test.properties"})*/
public class UserServiceImpl implements UserService {

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${url}")
    private String url;

    @Value("${urlTest}")
    private String urlTest;

    @Value("${port}")
    private String port;

    @Resource
    UserMapper userMapper;

    @Override
    public UserEntity queryUserInfo(String userName, String passdWord) {
        return userMapper.querUserInfoByInfo(userName,passdWord);
    }

}
