package com.dscomm.iecs.accept.hangzhou.service;

import com.dscomm.iecs.accept.hangzhou.bean.IncidentInformationBean;
import com.dscomm.iecs.accept.hangzhou.bean.IncidentShowBean;
import com.dscomm.iecs.base.graphql.inputbean.PaginationInputInfo;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

public interface IncidentInformationService {

    /**获取当日火情、抢险数量及最新警情信息*/
    IncidentInformationBean showIncidentInformation();

    /**获取当日24小时警情信息*/
    PaginationBean<IncidentShowBean> twentyFourHoursIncident(PaginationInputInfo paginationInputInfo);

}
