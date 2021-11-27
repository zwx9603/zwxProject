package com.dscomm.iecs.accept.graphql.fireinputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;

import java.util.List;

public class PlanWordQueryInputInfo {

    private Boolean whetherPage = false ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

    public PaginationInputInfo getPagination() {
        return pagination;
    }

    public void setPagination(PaginationInputInfo pagination) {
        this.pagination = pagination;
    }

    public Boolean getWhetherPage() {
        return whetherPage;
    }

    public void setWhetherPage(Boolean whetherPage) {
        this.whetherPage = whetherPage;
    }

    private List<AttachmentSaveInputInfo> attachmentSaveInputInfo;

    public List<AttachmentSaveInputInfo> getAttachmentSaveInputInfo() {
        return attachmentSaveInputInfo;
    }

    public void setAttachmentSaveInputInfo(List<AttachmentSaveInputInfo> attachmentSaveInputInfo) {
        this.attachmentSaveInputInfo = attachmentSaveInputInfo;
    }


}
