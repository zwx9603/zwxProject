package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.EarlyWarningImportantSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.EarlyWarningImportantBean;

import java.util.List;

public interface EarlyWarningImportantService {

    //重大预警信息根据时间查询
    List<EarlyWarningImportantBean> findEarlyWarningImportant ( String currentTime );

    //删除重大预警信息
    Boolean removeEarlyWarningImportant(String id);

    //id查询重大预警详情
    EarlyWarningImportantBean findEarlyWarningImportantById(String id);

    //保存查询重大预警
    EarlyWarningImportantBean saveEarlyWarningImportant(EarlyWarningImportantSaveInputInfo queryBean);
}
