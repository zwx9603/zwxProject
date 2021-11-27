package com.dscomm.iecs.accept.graphql.typebean;

import java.io.Serializable;

public class FireDzBean implements Serializable, Comparable<FireDzBean>{
    private String zGZd; // 中队的名称
    private String zdJl; // 中队到案发点的距离
    private String cllx; // 车辆类型
    private String zddm; // 中队代码
    private String cljl; // 车辆距离
    private String zfdjl; // 驻防点到案发点的距离
    private String zfdcpcl; // 驻防点可派车辆

    @Override
    public int compareTo(FireDzBean o) {
        return 0;
    }

    public FireDzBean() {
    }

    public FireDzBean(String zGZd, String zdJl, String cllx, String zddm, String cljl) {
        this.zGZd = zGZd;
        this.zdJl = zdJl;
        this.cllx = cllx;
        this.zddm = zddm;
        this.cljl = cljl;
    }

    public String getzGZd() {
        return zGZd;
    }

    public void setzGZd(String zGZd) {
        this.zGZd = zGZd;
    }

    public String getZdJl() {
        return zdJl;
    }

    public void setZdJl(String zdJl) {
        this.zdJl = zdJl;
    }

    public String getCljl() {
        return cljl;
    }

    public void setCljl(String cljl) {
        this.cljl = cljl;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getZddm() {
        return zddm;
    }

    public void setZddm(String zddm) {
        this.zddm = zddm;
    }

    public String getZfdjl() {
        return zfdjl;
    }

    public void setZfdjl(String zfdjl) {
        this.zfdjl = zfdjl;
    }

    public String getZfdcpcl() {
        return zfdcpcl;
    }

    public void setZfdcpcl(String zfdcpcl) {
        this.zfdcpcl = zfdcpcl;
    }
}
