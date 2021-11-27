package com.dscomm.iecs.accept.graphql.inputbean;

public class FireDzInputBean {
    private String fireZb; // 火灾的坐标
//    private String zdName; // 中队的名字
    private String zdzb; // 中队的坐标
    private String zddm; // 中队的代码
    private String zfdzb; // 驻防点坐标

    public String getFireZb() {
        return fireZb;
    }

    public void setFireZb(String fireZb) {
        this.fireZb = fireZb;
    }

    /*public String getZdName() {
        return zdName;
    }

    public void setZdName(String zdName) {
        this.zdName = zdName;
    }
*/
    public String getZdzb() {
        return zdzb;
    }

    public void setZdzb(String zdzb) {
        this.zdzb = zdzb;
    }

    public String getZddm() {
        return zddm;
    }

    public void setZddm(String zddm) {
        this.zddm = zddm;
    }

    public String getZfdzb() {
        return zfdzb;
    }

    public void setZfdzb(String zfdzb) {
        this.zfdzb = zfdzb;
    }
}
