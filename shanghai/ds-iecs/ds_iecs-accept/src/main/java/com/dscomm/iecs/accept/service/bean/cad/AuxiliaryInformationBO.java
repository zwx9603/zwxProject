package com.dscomm.iecs.accept.service.bean.cad;

/**
 * 描述:辅助信息
 *
 * @author XuZhiHeng
 * Date Time 2020/9/3 11:54
 */
public class AuxiliaryInformationBO {
    /**
     * 流水号
     */
    private String id;
    /**
     * 事件单编号
     */
    private String incidentId;
    /**
     * 辅助类型
     */
    private Integer auxiliaryType;
    /**
     * 地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 扩展信息1
     */
    private String exInformation1;
    /**
     * 扩展信息2
     */
    private String exInformation2;
    /**
     * 扩展信息3
     */
    private String exInformation3;

    /**
     * 保存时间
     */
    private Long saveTime;
    /**
     * 时间戳
     */
    private Long sjc;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getIncidentId() { return incidentId; }

    public void setIncidentId(String incidentId) { this.incidentId = incidentId; }

    public Integer getAuxiliaryType() { return auxiliaryType; }

    public void setAuxiliaryType(Integer auxiliaryType) { this.auxiliaryType = auxiliaryType; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getContactPhone() { return contactPhone; }

    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public String getExInformation1() { return exInformation1; }

    public void setExInformation1(String exInformation1) { this.exInformation1 = exInformation1; }

    public String getExInformation2() { return exInformation2; }

    public void setExInformation2(String exInformation2) { this.exInformation2 = exInformation2; }

    public String getExInformation3() { return exInformation3; }

    public void setExInformation3(String exInformation3) { this.exInformation3 = exInformation3; }

    public Long getSaveTime() { return saveTime; }

    public void setSaveTime(Long saveTime) { this.saveTime = saveTime; }

    public Long getSjc() { return sjc; }

    public void setSjc(Long sjc) { this.sjc = sjc; }
}

