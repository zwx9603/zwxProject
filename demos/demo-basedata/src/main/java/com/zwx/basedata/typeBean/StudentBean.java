package com.zwx.basedata.typeBean;

import lombok.Data;

@Data
public class StudentBean {
    private String sno;
    private String name;
    private String age;
    private String sex;
    private String tel;

    public StudentBean(String sno, String name, String age, String sex, String tel) {
        this.sno = sno;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.tel = tel;
    }
    public StudentBean(){}
}
