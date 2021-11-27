package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.IncidentMergeSaveInputInfo;


/**
 * 描述：警情合并记录 服务
 */
public interface IncidentMergeService {


    /**
     *  保存 警情合并记录
     * @param queryBean 保存参数
     * @return 返回结果
     */
    Boolean  saveIncidentMergeRecord( IncidentMergeSaveInputInfo queryBean ) ;

}
