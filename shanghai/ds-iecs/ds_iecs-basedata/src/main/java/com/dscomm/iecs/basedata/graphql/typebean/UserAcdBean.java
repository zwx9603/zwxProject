package com.dscomm.iecs.basedata.graphql.typebean;

/**
 * 描述 : 用户acd bo
 *
 * @author : ZhaiYanTao
 * Date Time : 2019/10/11 11:17
 */
public class UserAcdBean {
    /**
     * 流水号
     */
    private String id;
    /**
     * 工号
     */
    private String userId;
    /**
     * acd
     */
    private String acd;
    /**
     * 技能级别
     */
    private Integer skillLevel;
    /**
     * 时间戳
     */
    private Long sjc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
