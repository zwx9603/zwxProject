package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JCJ_ZZXXK")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CombatInformationEntity extends BaseEntity {

    @Column(name = "xfjgdm" , length = 100)
    private String organizationId; //所属消防机构代码

    @Column(name = "dwmc",length = 1000)
    private String unitName;//单位名称

    @Column(name = "dwdz",length = 1000)
    private String unitAddress;//单位地址

    @Column(name = "wjlj",length = 2000)
    private String fileURL;//文件存放路径

    @Column(name = "wjm",length = 1000)
    private String fileName;//文件名

    @Column(name = "scsj")
    private Long uploadTime;//上传时间

    @Column(name = "dzjc",length = 1000)
    private String addressShortName;//地址首字母简称

    @Column(name = "dwjc",length = 1000)
    private String unitShortName;//单位简称

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getAddressShortName() {
        return addressShortName;
    }

    public void setAddressShortName(String addressShortName) {
        this.addressShortName = addressShortName;
    }

    public String getUnitShortName() {
        return unitShortName;
    }

    public void setUnitShortName(String unitShortName) {
        this.unitShortName = unitShortName;
    }
}
