package com.dscomm.iecs.accept.graphql.inputbean;


import java.util.List;

/**
 * 描述：预警信息表
 *
 */

public class EarlyWarningListSaveInputInfo {

    private List<EarlyWarningSaveInputInfo>   earlyWarningList ; //预警信息

    public List<EarlyWarningSaveInputInfo> getEarlyWarningList() {
        return earlyWarningList;
    }

    public void setEarlyWarningList(List<EarlyWarningSaveInputInfo> earlyWarningList) {
        this.earlyWarningList = earlyWarningList;
    }
}
