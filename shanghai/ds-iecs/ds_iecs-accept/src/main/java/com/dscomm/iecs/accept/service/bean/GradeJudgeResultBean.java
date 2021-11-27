package com.dscomm.iecs.accept.service.bean;

/**
 * 智能推荐级别服务返回结果
 */
public class GradeJudgeResultBean {

    private String fire_level;//警情级别
    private Integer time;//运算时间

    private String id;//id

    public String getFire_level() {
        return fire_level;
    }

    public void setFire_level(String fire_level) {
        this.fire_level = fire_level;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
