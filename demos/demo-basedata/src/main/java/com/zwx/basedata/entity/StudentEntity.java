package com.zwx.basedata.entity;

import lombok.Data;

import javax.persistence.*;

@Data
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sno;

    private String name;

    private String age;

    private String sex;

    private String tel;

    public StudentEntity() {
    }

    public StudentEntity(String sno, String name, String age, String sex, String tel) {
        this.sno = sno;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.tel = tel;
    }
}
