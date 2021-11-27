package com.dscomm.iecs.accept.restful.vo.ReportVO;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 装备调派上报VO
 * */
public class DispatchEquipmentDTO {

    @JSONField(name = "ID")
    private String ID ;//装备调派id

    @JSONField(name = "CDDID")
    private String CDDID ;//调派单id

    @JSONField(name = "XFJGDM")
    private String XFJGDM ;//消防机构代码

    @JSONField(name = "QCLXDM")
    private String QCLXDM ;//器材类型代码

    @JSONField(name = "DPSL")
    private String DPSL ;//调派数量

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

    public String getXFJGDM() {
        return XFJGDM;
    }

    public void setXFJGDM(String XFJGDM) {
        this.XFJGDM = XFJGDM;
    }

    public String getQCLXDM() {
        return QCLXDM;
    }

    public void setQCLXDM(String QCLXDM) {
        this.QCLXDM = QCLXDM;
    }

    public String getDPSL() {
        return DPSL;
    }

    public void setDPSL(String DPSL) {
        this.DPSL = DPSL;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
