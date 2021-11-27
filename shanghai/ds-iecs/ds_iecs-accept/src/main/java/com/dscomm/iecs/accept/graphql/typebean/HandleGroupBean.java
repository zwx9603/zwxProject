package com.dscomm.iecs.accept.graphql.typebean;

import com.dscomm.iecs.base.service.bean.BaseBean;

import java.util.List;

/**
 *  描述: 处警记录（调派记录） 主要区分 首次派出  二次派车   派车根据单位分组
 *
 */
public class HandleGroupBean extends BaseBean {

    private String incidentId; // 案件信息ID

    private String handleBatch  ;// 派车批次  分组后 只存在 1 首次派车  2 二次派车（ 增援派车  ）

    private List<HandleOrganizationGroupBean> handleOrganizationBean ; //调派单位信息 （ 处警信息 ）分组

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getHandleBatch() {
        return handleBatch;
    }

    public void setHandleBatch(String handleBatch) {
        this.handleBatch = handleBatch;
    }

    public List<HandleOrganizationGroupBean> getHandleOrganizationBean() {
        return handleOrganizationBean;
    }

    public void setHandleOrganizationBean(List<HandleOrganizationGroupBean> handleOrganizationBean) {
        this.handleOrganizationBean = handleOrganizationBean;
    }
}
