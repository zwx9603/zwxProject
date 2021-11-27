package com.zwx.basedata.until.transformUtil;

import com.zwx.basedata.entity.StudentEntity;
import com.zwx.basedata.typeBean.StudentBean;

public class StudentTransformUtil {

    public static StudentBean transform(StudentEntity stuInfo){
        StudentBean studentBean = new StudentBean();
        studentBean.setSno(stuInfo.getSno());
        studentBean.setName(stuInfo.getName());
        studentBean.setSex(stuInfo.getSex());
        studentBean.setAge(stuInfo.getAge());
        studentBean.setTel(stuInfo.getTel());
        return studentBean;
    }
}
