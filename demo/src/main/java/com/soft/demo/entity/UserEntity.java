package com.soft.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntity implements Serializable {
    private Integer id;
    private String userName;
    private String passdWord;
    private String reallName;

    public UserEntity() {
    }

    public UserEntity(Integer id, String userName, String passdWord, String reallName) {
        this.id = id;
        this.userName = userName;
        this.passdWord = passdWord;
        this.reallName = reallName;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passdWord='" + passdWord + '\'' +
                ", reallName='" + reallName + '\'' +
                '}';
    }
}
