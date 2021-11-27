package com.dscomm.iecs.basedata.dal.po;

import com.dscomm.iecs.base.dal.po.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述 : 用户acd实体类
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/10/11 10:51
 */
@Entity
@Table(name = "jcj_bd_yhacd")
public class UserAcdEntity extends BaseEntity {

    /**
     * 工号
     */
    @Column(name = "gh", nullable = false)
    private String userId;
    /**
     * acd
     */
    @Column(name = "acd", length = 10, nullable = false)
    private String acd;
    /**
     * 技能级别
     */
    @Column(name = "jnjb", nullable = false)
    private Integer skillLevel;
    /**
     * 时间戳
     */
    @Column(name = "sjc")
    private Long sjc;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAcd() {
        return acd;
    }

    public void setAcd(String acd) {
        this.acd = acd;
    }

    public Integer getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Integer skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Long getSjc() {
        return sjc;
    }

    public void setSjc(Long sjc) {
        this.sjc = sjc;
    }
}
