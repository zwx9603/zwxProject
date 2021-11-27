package com.dscomm.iecs.accept.graphql.typebean;


/**
 * 要事提醒显示信息
 *
 * */


public class ImportantReminderBean {

    private String id;
    private String title;           //要事标题
    private String context;          //要事内容
    private String personId;         //填写人id
    private String personName;   //填写人姓名
    private String personUnitId;   //填写人单位id
    private String personUnitName; //填写人单位名称
    private Long releaseTime;       //发布时间
    private Long beginTime;       //开始时间
    private Long endTime;         //结束时间
    private String remarks ;        //备注


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonUnitId() {
        return personUnitId;
    }

    public void setPersonUnitId(String personUnitId) {
        this.personUnitId = personUnitId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonUnitName() {
        return personUnitName;
    }

    public void setPersonUnitName(String personUnitName) {
        this.personUnitName = personUnitName;
    }

    public Long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }



    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
