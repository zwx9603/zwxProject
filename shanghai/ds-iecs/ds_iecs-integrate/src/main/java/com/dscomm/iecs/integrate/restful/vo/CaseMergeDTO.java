package com.dscomm.iecs.integrate.restful.vo;

import java.util.List;

public class CaseMergeDTO {

    private String caseid ;//主案件id
    private List<ChildCaseId> childCase ;//被合并案件id

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public List<ChildCaseId> getChildCase() {
        return childCase;
    }

    public void setChildCase(List<ChildCaseId> childCase) {
        this.childCase = childCase;
    }
}
