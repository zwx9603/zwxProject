package com.dscomm.iecs.agent.graphql.typebean;


/**
 * 描述:违规信息
 *
 */
public class ViolationBean {
    private String code;//违规类型code
    private String name;//违规类型编码
    private Long violateTime;//违规时间
    private String handleDescription;//处置描述

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getViolateTime() {
        return violateTime;
    }

    public void setViolateTime(Long violateTime) {
        this.violateTime = violateTime;
    }

    public String getHandleDescription() {
        return handleDescription;
    }

    public void setHandleDescription(String handleDescription) {
        this.handleDescription = handleDescription;
    }
}
