package com.dscomm.iecs.accept.restful.vo.ReportVO;

import java.util.List;

public class CaseMergeDTO {

    private String caseid ;//主案件id
    private List<ChildCaseIdDTO> childCase ;//被合并案件id

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public List<ChildCaseIdDTO> getChildCase() {
        return childCase;
    }

    public void setChildCase(List<ChildCaseIdDTO> childCase) {
        this.childCase = childCase;
    }
}
