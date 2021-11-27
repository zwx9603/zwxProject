package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.NewsCircularEntity;
import com.dscomm.iecs.accept.dal.po.NewsCircularReceiverEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * 描述： 通知消息
 *
 */
public interface NewsCircularRepository extends Repository<NewsCircularEntity, String> {

    /**
     * 根据条件 查询 通知信息
     */
    public List<NewsCircularEntity> findNewsCircularCondition( Long   startTime , Long   endTime ,  List<String> type ,
                                                             List<String> source , String keyword ,String titleKeyword , String contentKeyword ,  Boolean whetherPage,
                                                             int page, int size ) ;


    /**
     * 根据条件 查询 通知信息 总数
     */
    Integer findNewsCircularConditionTotal ( Long   startTime , Long   endTime ,  List<String> type ,
                                           List<String> source , String keyword ,String titleKeyword , String contentKeyword   ) ;




    /**
     * 根据条件 查询 通知接收信息
     */
    public List<NewsCircularReceiverEntity> findNewsCircularReceiverCondition( Long   startTime , Long   endTime ,  List<String> type ,
                                                               List<String> source , String keyword ,String titleKeyword , String contentKeyword ,
                                                               String receivingObjectId , List<Integer>   newsCircularStatus ,   Boolean whetherPage,
                                                               int page, int size ) ;


    /**
     * 根据条件 查询 通知接收信息 总数
     */
    Integer findNewsCircularReceiverConditionTotal ( Long   startTime , Long   endTime ,  List<String> type ,
                                             List<String> source , String keyword ,String titleKeyword , String contentKeyword , String receivingObjectId ,List<Integer>   newsCircularStatus   ) ;

    /**
     * 根据接口对象id
     */
    @Query(" select t from NewsCircularReceiverEntity t   where  t.valid = 1    and t.receiverObjectId = ?1 and t.newsCircularStatus = ?2  " +
            "  order by   t.circularTime desc    " )
    public List<NewsCircularReceiverEntity> findNewsCircularReceiverCondition( String incidentId  , Integer newsCircularStatus ) ;


    /**
     * 根据通知信息ids 查询通知信息
     */
    @Query(" select t from NewsCircularEntity t   where  t.valid = 1  " +
            " and t.id in  ( ?1 )     " )
    public List<NewsCircularEntity> findNewsCircular(  List<String> newsCircularIds   ) ;

    /**
     * 根据通知信息ids 查询通知信息接收者
     */
    @Query(" select t from NewsCircularReceiverEntity t   where  t.valid = 1  " +
            " and t.newsCircularId in  ( ?1 )     order by  t.circularTime  asc  " )
    public List<NewsCircularReceiverEntity> findNewsCircularReceiver (  List<String> newsCircularIds  ) ;


    @Query(" select t  from NewsCircularReceiverEntity t  " +
            " where t.valid=1 and t.id  in ( ?1 )   order by t.circularTime desc  ")
    List<NewsCircularReceiverEntity> findNewsCircularReceiverByIds (List<String> newsCircularReceiverIds  );







}
