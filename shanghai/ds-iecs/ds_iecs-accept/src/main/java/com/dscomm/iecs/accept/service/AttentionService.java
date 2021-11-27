package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.typebean.AttentionBean;

/**
 * 描述:警情关注服务
 *
 */
public interface AttentionService {
    /**
     * 关注服务
     * @return 返回保存后的信息
     */
    AttentionBean saveAttention(String incidentId , Integer attentionType  , Integer type  , Integer attentionWay , String attentionReason   );

    /**
     * 取消关注服务
     * @return 返回删除结果
     */
    Boolean deleteAttention( String incidentId , Integer attentionType , Integer type  ,      Integer attentionWay  );


    /**
     * 根据 重要警情警情id   获得 是否存在 关注信息
     * @return 返回删除结果
     */
    Boolean existImportantAttention( String incidentId    );

}
