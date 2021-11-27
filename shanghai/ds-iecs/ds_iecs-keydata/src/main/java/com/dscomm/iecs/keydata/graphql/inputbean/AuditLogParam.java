package com.dscomm.iecs.keydata.graphql.inputbean;

import java.util.List;

/**
 * 描述:审计日志查询参数
 *
 * @author YangFuxi
 * Date Time 2021/5/30 19:38
 */
public class AuditLogParam {
    private List<String> operateTypes;
    private Long startTime;
    private Long endTime;
    private String documentNumber;

    public List<String> getOperateTypes() {
        return operateTypes;
    }

    public void setOperateTypes(List<String> operateTypes) {
        this.operateTypes = operateTypes;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
