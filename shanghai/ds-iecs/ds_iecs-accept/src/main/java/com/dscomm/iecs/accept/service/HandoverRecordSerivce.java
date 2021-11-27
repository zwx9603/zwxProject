package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.HandoverRecordQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.HandoverRecordSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.HandoverRecordBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

import java.util.List;

/**
 * 交接班日志服务
 */
public interface HandoverRecordSerivce {

    /**
     * 保存交接班日志
     * @param inputInfo
     * @return HandoverRecordBean
     * */
    HandoverRecordBean saveHandoverRecord(HandoverRecordSaveInputInfo inputInfo);

    /**
     * 查询交接班日志
     * @param queryBean 起止时间+留言人内容模糊匹配
     * @return  HandoverRecordBean列表分页
     * */
    PaginationBean<HandoverRecordBean> findHandoverRecordList (HandoverRecordQueryInputInfo queryBean);

    /**
     * 删除交接班日志
     * @param ids 日志id集合
     * */
    Boolean deleteHandoverRecord(List<String> ids);

    /**
     * 查询交接班日志(restful接口)
     * @param queryBean
     * @return
     */
    PaginationBean<HandoverRecordBean> findHandoverRecordRestList(HandoverRecordQueryInputInfo queryBean);

    /**
     * 保存交接班日志(restful接口)
     * @param queryBean
     * @return
     */

    HandoverRecordBean saveHandoverRecordRest(HandoverRecordSaveInputInfo queryBean);
}
