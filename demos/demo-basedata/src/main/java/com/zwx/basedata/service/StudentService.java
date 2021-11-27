package com.zwx.basedata.service;

import com.zwx.basedata.entity.StudentEntity;
import com.zwx.basedata.typeBean.StudentBean;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Map<String, StudentBean> modifyStudentCache(String sno,StudentBean studentBean,String var);

    Map<String,StudentBean> findStudentCache();

    /*
     * 查询学生类的信息,并放入缓存
     * */
    List<StudentBean> queryStudentInfo();

    /*
    * 获取所有的学生信息
    * */
    List<StudentBean> findAllStuCache();

    /*
    * 根据条件查询学生信息
    * */
    List<StudentBean> findStudentCondition(StudentEntity studentEntity);

    /*
    * 添加学生信息
    * */
    StudentBean saveStudent(StudentEntity studentEntity);

    /*
    * 修改学生信息
    * */
    StudentBean updateStudent(StudentEntity studentEntity);

    /*
    * 删除学生信息
    * */
    Boolean deleteStudent(String sno);
}
