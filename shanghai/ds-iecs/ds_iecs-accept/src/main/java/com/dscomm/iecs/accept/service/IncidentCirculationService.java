package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.IncidentCirculationBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentCirculationConfirmBean;

/**
 * 描述:警情接管服务
 *
 * @author YangFuxi
 * Date Time 2019/9/5 19:32
 */
public interface  IncidentCirculationService {
    /**
     * 请求接管服务
     *
     * @param bean 请求接管信息
     * @return 返回接管结果
     */
    Boolean circulation(IncidentCirculationBean bean);

    /**
     * 警情流转成功确认
     *
     * @param bean 流转信息
     * @return 返回通知结果
     */
    Boolean notifyCirculationSuccess(IncidentCirculationConfirmBean bean);
}
