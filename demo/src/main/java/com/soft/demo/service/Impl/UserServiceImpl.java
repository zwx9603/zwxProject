package com.soft.demo.service.Impl;

import com.soft.demo.bean.StudentBean;
import com.soft.demo.bean.UserBean;
import com.soft.demo.entity.StudentEntity;
import com.soft.demo.entity.UserEntity;
import com.soft.demo.mapper.UserMapper;
import com.soft.demo.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
/*@PropertySource(value = {"classpath:config/data.properties",
        "classpath:application-dev.yml",
        "classpath:config/test.properties"})*/
@CacheConfig(cacheNames = "basic_user")
public class UserServiceImpl implements UserService {

    public static Map<String, UserBean> userCacheMap = new HashMap<>();
    public static Map<String, StudentBean> studentCacheMap = new HashMap<>();

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${url}")
    private String url;

    @Value("${urlTest}")
    private String urlTest;

    @Value("${port}")
    private String port;
    @Autowired
    UserMapper userMapper;

    @Autowired
    Environment environment;


    @Override
    public UserEntity queryUserInfo(String userName, String passdWord) {
        return userMapper.querUserInfoByInfo(userName,passdWord);
    }

    @Cacheable
    public List<UserBean> queryUserInfo(){
        List<UserEntity> userEntities = userMapper.querUserInfo();
        List<UserBean> transform = new ArrayList<>();
        if(userEntities != null){
            for (UserEntity ele: userEntities) {
                UserBean transform1 = transform(ele);
                transform.add(transform1);
                userCacheMap.put(String.valueOf(transform1.getId()),transform1);
            }
        }
        return transform;
    }

    @Cacheable(key="#id")
    @Override
    public UserBean queryInfoByCache(String id) {
        if(Strings.isNotBlank(id)){
            return userCacheMap.get(id);
        }
        return null;
    }

    @Override
    @Cacheable
    public List<StudentBean> queryStudentInfo() {
        List<StudentEntity> studentEntities = userMapper.queryStudentInfo();
        List<StudentBean> transform = new ArrayList<>();
        if(studentEntities != null){
            for (StudentEntity ele:studentEntities) {
                StudentBean transform1 = transform(ele);
                transform.add(transform1);
                studentCacheMap.put(String.valueOf(transform1.getSno()),transform1);
            }
        }
        return transform;
    }

    @Override
    public StudentBean queryStuInfoByCache(String sno) {
        if(Strings.isNotBlank(sno)){
            return studentCacheMap.get(sno);
        }
        return null;
    }

    @Override
    public String testInfo() {
        return "activa:" + active + "    ,url:" + url + "    ,port:" + port +
                "    ,urlTest:" + urlTest;
//        return "";
    }

    @Override
    public String testEnvironment() {
        String tel = environment.getProperty("tel");
        return tel;
    }

    public UserBean transform(UserEntity userEntity){
        UserBean userBean = new UserBean();
        userBean.setId(userEntity.getId());
        userBean.setUserName(userEntity.getUserName());
        if(userEntity.getPassdWord().length() >= 15){
            String passdWord = userEntity.getPassdWord();
            userBean.setPassWord(passdWord.substring(0,3));
        }else{
            userBean.setPassWord(userEntity.getPassdWord());
        }
        userBean.setPassWord(userEntity.getPassdWord());
        userBean.setReallName(userEntity.getReallName());
        return userBean;
    }

    public StudentBean transform(StudentEntity stuInfo){
        StudentBean studentBean = new StudentBean();
        studentBean.setSno(stuInfo.getSno());
        studentBean.setName(stuInfo.getName());
        studentBean.setSex(stuInfo.getSex());
        studentBean.setAge(stuInfo.getAge());
        studentBean.setTel(stuInfo.getTel());
        return studentBean;
    }
}
