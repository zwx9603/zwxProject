package com.zwx.basedata.service;

import com.zwx.basedata.entity.UserEntity;

public interface UserService {

    UserEntity queryUserInfo(String userName, String passdWord);

}
