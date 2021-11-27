package com.dscomm.iecs.keydata.service;


import com.dscomm.iecs.keydata.graphql.inputbean.KeyDataChangeRecordSaveInputInfo;
import com.dscomm.iecs.keydata.graphql.typebean.KeyDataChangeRecordBean;

import java.util.List;

/**
 * 描述:关键数据变更记录 service服务
 *
 * @author ZhaiYanTao
 * Date Time 2019/8/19 10:49
 */
public interface KeyDataChangeRecordService {


    /**
     * 保存关键数据变更记录
     *
     * @param queryBean 要保存的关键数据变更记录BO
     * @return 保存成功的关键数据变更记录BO
     */
     KeyDataChangeRecordBean  saveKeyDataChangeRecord( KeyDataChangeRecordSaveInputInfo  queryBean);

    /**
     * 保存关键数据变更记录
     *
     * @param queryBean 要保存的关键数据变更记录BO
     * @return 保存成功的关键数据变更记录BO
     */
    List<KeyDataChangeRecordBean> saveKeyDataChangeRecord(List<KeyDataChangeRecordSaveInputInfo> queryBean);

}
