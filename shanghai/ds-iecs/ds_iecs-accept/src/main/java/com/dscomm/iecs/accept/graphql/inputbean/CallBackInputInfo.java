package com.dscomm.iecs.accept.graphql.inputbean;

/**
 * 描述:回拨参数BO
 *
 */
public class CallBackInputInfo {
    private String callingPhone;//呼出方电话
    private String calledPhone;//被呼叫方电话
    private String incidentId;//事件单编号
    //TODO:回拨逻辑暂未确定，待确定后更改(zhujianmin20200717)
//    private Integer type;//回呼类型（0报警，1接警回呼，2其他）默认0报警

    public String getCallingPhone() {
        return callingPhone;
    }

    public void setCallingPhone(String callingPhone) {
        this.callingPhone = callingPhone;
    }

    public String getCalledPhone() {
        return calledPhone;
    }

    public void setCalledPhone(String calledPhone) {
        this.calledPhone = calledPhone;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

//    public Integer getType() {
//        return type;
//    }
//
//    public void setType(Integer type) {
//        this.type = type;
//    }
}
