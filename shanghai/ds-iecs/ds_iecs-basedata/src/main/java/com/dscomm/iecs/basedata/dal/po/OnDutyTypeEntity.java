package com.dscomm.iecs.basedata.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述： 值班类型
 */
@Entity
@Table(name = "SWGL_ZBGL_ZBLX")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OnDutyTypeEntity extends BaseEntity {

    @Column(name = "LXMC", length =  200 )
    private String typeDescribe;  //岗位名称

    @Column(name = "ZBRC", length =  10 )
    private String onDutyNumber;   //值班人次

    @Column(name = "XSXH")
    private Integer showNumber;    //显示序号

    @Column(name = "SFQY", length =  10 )
    private String enable;     //是否启用

    @Column(name = "SFTB", length =  10 )
    private String synchronization;    //是否通报

    @Column(name = "GWZZ", length =  400 )
    private String responsibilities;    //岗位职责

    @Column(name = "SSDW", length =  100 )
    private String subordinateOrganization;     //所属单位

    @Column(name = "LBID", length =  100 )
    private String onDutyCategory;   //值班类别ID

    @Column(name = "ADDIP", length =  100 )
    private String createIp;         //创建ip

    @Column(name = "ADDACC", length =  100 )
    private String createPersonId;    //创建人id

    @Column(name = "ADDACCNAME", length =  100 )
    private String createPersonName;  //创建人姓名

    @Column(name = "ADDTIME")
    private Long createTime;        //创建时间

    @Column(name = "CHGIP", length =  100 )
    private String updateIp;         //修改ip

    @Column(name = "CHGACC", length =  100 )
    private String updateId;         //修改人id

    @Column(name = "CHGACCNAME", length =  100 )
    private String updateName;         //修改人姓名

    @Column(name = "CHGTIME")
    private Long updateTime;  //修改时间

    @Column(name = "DELETED", length =  10 )
    private String deleted;         //删除标记

    @Column(name = "JLZT"   )
    private Integer JLZT;//记录状态

    @Column(name = "CSZT"  )
    private Integer CSZT;//传输状态

    @Column(name = "SJC")
    private Long SJC ;//时间戳

    @Column(name = "SJBB", length = 100)
    private String SJBB ;//数据库版本

    @Column(name = "YWXTBSID", length = 100)
    private String YWXTBSID ;//业务系统部署ID

    @Column(name = "JKSJBB", length = 100)
    private String JKSJBB ;//基础数据版本

    public String getTypeDescribe() {
        return typeDescribe;
    }

    public void setTypeDescribe(String typeDescribe) {
        this.typeDescribe = typeDescribe;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getSubordinateOrganization() {
        return subordinateOrganization;
    }

    public void setSubordinateOrganization(String subordinateOrganization) {
        this.subordinateOrganization = subordinateOrganization;
    }

    public String getOnDutyCategory() {
        return onDutyCategory;
    }

    public void setOnDutyCategory(String onDutyCategory) {
        this.onDutyCategory = onDutyCategory;
    }

    public String getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    public String getCreatePersonName() {
        return createPersonName;
    }

    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }


    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }


    public Integer getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(Integer showNumber) {
        this.showNumber = showNumber;
    }

    public String getOnDutyNumber() {
        return onDutyNumber;
    }

    public void setOnDutyNumber(String onDutyNumber) {
        this.onDutyNumber = onDutyNumber;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }



    public String getSynchronization() {
        return synchronization;
    }



    public void setSynchronization(String synchronization) {
        this.synchronization = synchronization;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Integer getJLZT() {
        return JLZT;
    }

    public void setJLZT(Integer JLZT) {
        this.JLZT = JLZT;
    }

    public Integer getCSZT() {
        return CSZT;
    }

    public void setCSZT(Integer CSZT) {
        this.CSZT = CSZT;
    }

    public Long getSJC() {
        return SJC;
    }

    public void setSJC(Long SJC) {
        this.SJC = SJC;
    }

    public String getSJBB() {
        return SJBB;
    }

    public void setSJBB(String SJBB) {
        this.SJBB = SJBB;
    }

    public String getYWXTBSID() {
        return YWXTBSID;
    }

    public void setYWXTBSID(String YWXTBSID) {
        this.YWXTBSID = YWXTBSID;
    }

    public String getJKSJBB() {
        return JKSJBB;
    }

    public void setJKSJBB(String JKSJBB) {
        this.JKSJBB = JKSJBB;
    }
}
