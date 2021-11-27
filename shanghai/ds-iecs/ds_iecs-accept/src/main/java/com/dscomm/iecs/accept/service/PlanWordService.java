package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.fireinputbean.PlanWordQueryInputInfo;
import com.dscomm.iecs.accept.graphql.fireinputbean.PlanWordSaveInputInfo;
import com.dscomm.iecs.accept.graphql.firetypebean.PlanWordBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

import java.util.List;

public interface PlanWordService {

    //分页查询文本预案信息
    PaginationBean<PlanWordBean> findPlanWordCondition(PlanWordQueryInputInfo queryBean);

    //根据id查询文本预案详情
    PlanWordBean findPlanWord(String id);

    //保存文本预案详情
    PlanWordBean savePlanWord(PlanWordSaveInputInfo queryBean);

    //删除文本预案
    Boolean removePlanWord(String id);

    //通过重点单位查看文本预案
    List<PlanWordBean> findPlanWordByKeyUnitId(String keyUnitId);
}
