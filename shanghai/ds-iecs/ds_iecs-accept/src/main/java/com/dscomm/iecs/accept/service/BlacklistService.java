package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.BlackListQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.BlacklistSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.BlacklistBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

/**
 * 描述：黑名单 服务
 */
public interface BlacklistService {

    /**
     * 根据条件 查询黑名单
     *
     * @param queryBean 查询条件
     * @return 黑名单
     */
    PaginationBean<BlacklistBean> findBlacklistPhoneCondition( BlackListQueryInputInfo queryBean );

    /**
     * 保存黑名单
     *
     * @param inputInfo 黑名单保存参数
     * @return 保存结果
     */
    BlacklistBean saveBlacklist(BlacklistSaveInputInfo inputInfo);

    /**
     * 移除黑名单
     *
     * @param blacklistId 黑名单id
     * @return 撤销结果
     */
    Boolean removeBlacklist(String blacklistId);


    /**
     * 根据电话 黑名单
     *
     * @param phoneNumber 电话号码
     * @return 撤销结果
     */
    BlacklistBean findBlacklistByPhoneNumber( String phoneNumber);



}
