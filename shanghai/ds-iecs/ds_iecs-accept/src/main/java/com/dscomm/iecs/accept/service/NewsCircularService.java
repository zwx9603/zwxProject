package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.NewsCircularQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.NewsCircularSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.NewsCircularBean;
import com.dscomm.iecs.accept.graphql.typebean.NewsCircularReceiverBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

import java.util.List;

/**
 *  描述： 消息通知 服务
 */
public interface NewsCircularService {

    /**
     *保存消息通知
     */
    NewsCircularBean saveNewsCircular (  NewsCircularSaveInputInfo queryBean  );


    /**
     * 根据条件 查询消息通知
     */
    PaginationBean<NewsCircularBean> findNewsCircularCondition (NewsCircularQueryInputInfo queryBean   );


    /**
     *   查询 接收者的消息通知
     */
    PaginationBean<NewsCircularReceiverBean>  findNewsCircularReceiverCondition ( NewsCircularQueryInputInfo queryBean     );


    /**
     * 根据消息通知ids 变更消息通知服务状态 为已接收
     */
    Boolean  receiveNewsCircular ( List<String> newsCircularIds  );

}
