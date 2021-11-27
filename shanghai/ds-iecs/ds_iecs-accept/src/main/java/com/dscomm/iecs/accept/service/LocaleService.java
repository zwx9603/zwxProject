package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.LocaleQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.LocaleSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.LocaleBean;

import java.util.List;


/**
 * 描述：现场信息 服务
 */
public interface LocaleService {


    /**
     * 现场信息 保存
     * @param queryBean 保存参数
     * @return 返回结果
     */
    LocaleBean saveLocale(LocaleSaveInputInfo queryBean);

    LocaleBean saveLocaleMobile(LocaleSaveInputInfo queryBean);


    /**
     * 根据案件id查询现场信息（文字反馈）
     * @param incidentId 案件id
     * @return 返回结果
     */
    List<LocaleBean> findLocale(String incidentId);



    /**
     * 根据条件查询现场信息（文字反馈）
     * @param queryBean  查询条件
     * @return 返回结果
     */
    List<LocaleBean> findLocaleCondition( LocaleQueryInputInfo queryBean );





}
