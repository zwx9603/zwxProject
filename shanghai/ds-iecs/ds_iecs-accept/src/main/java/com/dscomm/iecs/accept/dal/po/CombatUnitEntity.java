package com.dscomm.iecs.accept.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述:作战单元实体
 * author:YangFuXi
 * Date:2021/6/8 Time:14:42
 **/
@Entity
@Table(name = "JCJ_ZZDY")
public class CombatUnitEntity extends BaseEntity {
    @Column(name = "mc")
    private String unitName;//作战单元名称
    @Column(name = "nr")
    private String content;//作战单元说明
    @Column(name = "dwid")
    private String orgId;//所属单位id
    @Column(name = "dwmc")
    private String orgName;//所属单位名称
    @Column(name = "fzrid")
    private String unitPersonId; //队长id
    @Column(name = "fzrmc")
    private String unitPersonName;//队长姓名
    @Column(name = "fzrlxdh")
    private String unitPersonPhone;//队长联系电话
    @Column(name = "bxrzh")
    private String authorAccount;//编写人账号
    @Column(name = "bxrmc")
    private String authorName;//编写人名称

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUnitPersonId() {
        return unitPersonId;
    }

    public void setUnitPersonId(String unitPersonId) {
        this.unitPersonId = unitPersonId;
    }

    public String getUnitPersonName() {
        return unitPersonName;
    }

    public void setUnitPersonName(String unitPersonName) {
        this.unitPersonName = unitPersonName;
    }

    public String getUnitPersonPhone() {
        return unitPersonPhone;
    }

    public void setUnitPersonPhone(String unitPersonPhone) {
        this.unitPersonPhone = unitPersonPhone;
    }

    public String getAuthorAccount() {
        return authorAccount;
    }

    public void setAuthorAccount(String authorAccount) {
        this.authorAccount = authorAccount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
