package com.dscomm.iecs.accept.restful.vo.ReportVO;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 派车上报VO
 * */
public class DispatchVehicleDTO {

    @JSONField(name = "ID")
    private String ID ;

    @JSONField(name = "CDDID")
    private String CDDID ;//处警单id

    @JSONField(name = "CLID")
    private String CLID ;//车辆id

    @JSONField(name = "CLLXDM")
    private String CLLXDM ;//车辆类型代码

    @JSONField(name = "CLMC")
    private String CLMC ;//车辆名称

    @JSONField(name = "CPHM")
    private String CPHM ;//车牌号码

    @JSONField(name = "XFJGDM")
    private String XFJGDM ;//消防机构编码

    @JSONField(name = "BZ")
    private String BZ ;//备注

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCDDID() {
        return CDDID;
    }

    public void setCDDID(String CDDID) {
        this.CDDID = CDDID;
    }

    public String getCLID() {
        return CLID;
    }

    public void setCLID(String CLID) {
        this.CLID = CLID;
    }

    public String getCLLXDM() {
        return CLLXDM;
    }

    public void setCLLXDM(String CLLXDM) {
        this.CLLXDM = CLLXDM;
    }

    public String getCLMC() {
        return CLMC;
    }

    public void setCLMC(String CLMC) {
        this.CLMC = CLMC;
    }

    public String getCPHM() {
        return CPHM;
    }

    public void setCPHM(String CPHM) {
        this.CPHM = CPHM;
    }

    public String getXFJGDM() {
        return XFJGDM;
    }

    public void setXFJGDM(String XFJGDM) {
        this.XFJGDM = XFJGDM;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
