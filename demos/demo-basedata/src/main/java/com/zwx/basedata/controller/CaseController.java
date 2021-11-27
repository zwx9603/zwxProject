package com.zwx.basedata.controller;

import com.zwx.basedata.exception.TestException;
import com.zwx.basedata.service.StudentService;
import com.zwx.basedata.service.UserService;
import com.zwx.basedata.typeBean.StudentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CaseController implements ApplicationRunner {

    @Autowired
    StudentService studentService;

    @Resource
    UserService userService;
    /*
    * 启动项目加载缓存
    * */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try{
            List<StudentBean> studentBeanList = studentService.queryStudentInfo();
            if(studentBeanList.size()>0 && studentBeanList != null){
                System.out.println(studentBeanList.get(0).toString());
            }
            /*UserEntity userEntity = userService.queryUserInfo("zwx", "123");
            System.out.println(userEntity.toString());*/

        }catch (Exception e){
            e.printStackTrace();
            throw new TestException("获取学生信息并放入缓存执行错误");
        }
    }
}
