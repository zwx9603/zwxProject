package com.dscomm.iecs.accept.graphql.inputbean;

import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;

/**非话务记录查询输入信息*/
public class UncallAcceptQueryInputInfo {

    private String senderPhoneNum;//发送人电话号码

    private Boolean whetherPage = true  ; //是否分页查询 默认分页

    private PaginationInputInfo pagination = new PaginationInputInfo(); //分页条件

    public String getSenderPhoneNum() {
        return senderPhoneNum;
    }

    public void setSenderPhoneNum(String senderPhoneNum) {
        this.senderPhoneNum = senderPhoneNum;
    }

    public Boolean getWhetherPage() {
        return whetherPage;
    }

    public void setWhetherPage(Boolean whetherPage) {
        this.whetherPage = whetherPage;
    }

    public PaginationInputInfo getPagination() {
        return pagination;
    }

    public void setPagination(PaginationInputInfo pagination) {
        this.pagination = pagination;
    }
}
