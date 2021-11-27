package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.MonitorQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.ViolationSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.DimensionAssembleStatisticsBean;
import com.dscomm.iecs.accept.graphql.typebean.ViolationRecordBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

/**
 *
 * 描述：违规操作记录 服务
 *
 */
public interface ViolationService {

    /**
     *  保存修改 违规操作
     * @param queryBean 保存修改违规操作记录参数
     * @return 返回结果
     */
    ViolationRecordBean saveViolation( ViolationSaveInputInfo queryBean ) ;

    /**
     * 根据时间段 坐席号 人员工号 获得所有违规操作
     * @param queryInputInfo 查询参数
     * @return 返回结果
     */
    PaginationBean<ViolationRecordBean> findViolationTimeSeatPersonNumber(MonitorQueryInputInfo queryInputInfo) ;



    /**
     * 根据时间段 坐席号 人员工号 获得所有违规操作统计信息
     * @param queryInputInfo 查询参数
     * @return 返回结果
     */
    DimensionAssembleStatisticsBean findSeatViolationStatistics(MonitorQueryInputInfo queryInputInfo) ;



}
