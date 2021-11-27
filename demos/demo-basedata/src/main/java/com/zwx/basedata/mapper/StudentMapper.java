package com.zwx.basedata.mapper;

import com.zwx.basedata.entity.StudentEntity;
import com.zwx.basedata.typeBean.StudentBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    /*
     * 查询学生类的信息
     * */
    List<StudentEntity> findAllStudent();

    /*
    * 根据条件查询学生信息
    * */
    List<StudentEntity> findStudentCondition(StudentEntity studentEntity);
    /*
     * 添加学生信息
     * */
    int saveStudent(StudentEntity studentEntity);

    /*
     * 修改学生信息
     * */
    int updateStudent(StudentEntity studentEntity);

    /*
     * 删除学生信息
     * */
    int deleteStudent(String sno);
}
