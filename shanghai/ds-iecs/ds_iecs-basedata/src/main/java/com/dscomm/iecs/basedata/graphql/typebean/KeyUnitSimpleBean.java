package com.dscomm.iecs.basedata.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

/**
 * 描述:灭火重点单位
 */
public class KeyUnitSimpleBean extends BaseBean {

    private String keyUnitId ; //重点单位id

    private String fireproofUnit;  //防火单位ID

    private String unitName;  //单位名称

    private String pinyinHeader; //单位名称拼音头

    private String unitShortName;  //单位拼音简称

    private String unitAddress;  //单位地址

    private String unitPhone;  //单位电话

    private String mailCode;  //单位邮政编码

    private String unitTypeCode; //单位类型

    private String unitClassCode; //单位分类 01防火单位 02灭火单位

    private String longitude;  //经度

    private String latitude;  //纬度

    private String geographicalPosition;  //地理位置

    private String organizationId; // 管辖消防机构id


    public String getUnitClassCode() {
        return unitClassCode;
    }

    public void setUnitClassCode(String unitClassCode) {
        this.unitClassCode = unitClassCode;
    }

    public String getKeyUnitId() {
        return keyUnitId;
    }

    public void setKeyUnitId(String keyUnitId) {
        this.keyUnitId = keyUnitId;
    }

    public String getUnitTypeCode() {
        return unitTypeCode;
    }

    public void setUnitTypeCode(String unitTypeCode) {
        this.unitTypeCode = unitTypeCode;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public String getFireproofUnit() {
        return fireproofUnit;
    }

    public void setFireproofUnit(String fireproofUnit) {
        this.fireproofUnit = fireproofUnit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPinyinHeader() {
        return pinyinHeader;
    }

    public void setPinyinHeader(String pinyinHeader) {
        this.pinyinHeader = pinyinHeader;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getGeographicalPosition() {
        return geographicalPosition;
    }

    public void setGeographicalPosition(String geographicalPosition) {
        this.geographicalPosition = geographicalPosition;
    }

    public String getUnitShortName() {
        return unitShortName;
    }

    public void setUnitShortName(String unitShortName) {
        this.unitShortName = unitShortName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}