package com.dscomm.iecs.accept.service.bean.cad;

/**
 * description: 警情补充信息
 *
 * @author YangFuxi
 * Date Time 2020/8/15 13:54
 */
public class IncidentAddInfoBO {
    private String acceptId;//受理单编号
    private String addInfo;//补充信息
    private Long addTime;//补充时间
    private String operatePersonCode;//操作人工号
    private String operatePersonName;//操作人姓名

    public String getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(String acceptId) {
        this.acceptId = acceptId;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getOperatePersonCode() {
        return operatePersonCode;
    }

    public void setOperatePersonCode(String operatePersonCode) {
        this.operatePersonCode = operatePersonCode;
    }

    public String getOperatePersonName() {
        return operatePersonName;
    }

    public void setOperatePersonName(String operatePersonName) {
        this.operatePersonName = operatePersonName;
    }
}
