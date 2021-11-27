package com.soft.demo.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.sql.DataSourceDefinition;

@Component
//@Configuration
//@PropertySource(value = {"classpath:config/data.properties","classpath:config/test.properties"})
//@ConfigurationProperties(prefix = "data")
public class MyDataSource1 {

    @Value("${urlTest}")
    private String urlTest;

    @Value("${userName}")
    private String userName;

    @Value("${sex}")
    private Integer sex;

    @Value("${tel}")
    private String tel;

    public String getUrlTest() {
        return urlTest;
    }

    public void setUrlTest(String urlTest) {
        this.urlTest = urlTest;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Data{" +
                "urlTest='" + urlTest + '\'' +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", tel='" + tel + '\'' +
                '}';
    }
}
