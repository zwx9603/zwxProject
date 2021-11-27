package com.dscomm.iecs.basedata.service;


import com.dscomm.iecs.basedata.graphql.inputbean.KeyUnitQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.KeyUnitSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitBean;
import com.dscomm.iecs.basedata.graphql.typebean.KeyUnitSimpleBean;

import java.util.List;
import java.util.Map;

/**
 *
 * 描述：重点单位 服务
 *
 */
public interface KeyUnitService {

    /** 强制更新全部重点单位缓存
     * @return 返回结果
     */
    void forceUpdateCacheKeyUnit( );


    /** 获得重点单位 id 与 重点单位简要信息
     * @return 返回结果
     */
    KeyUnitSimpleBean findKeyUnitSimple(  String keyUnitId );



    /** 获得重点单位 id 与 重点单位简要信息 Map
     * @return 返回结果
     */
    Map<String,KeyUnitSimpleBean>  findKeyUnitSimpleIds(  List<String> keyUnitIds );



    /** 获得重点单位 id 与 名称  Map
     * @return 返回结果
     */
    Map<String,String>  findKeyUnitIdNameMap(  List<String> keyUnitIds );


    /**
     * 获得全部重点单位信息
     * @return
     */
    List<KeyUnitSimpleBean> findKeyUnitAuthenticate ( String organizationId ) ;

    /**
     *  根据条件获得重点单位信息
     * @return
     */
    List<KeyUnitBean> findKeyUnitCondition(KeyUnitQueryInputInfo queryBean ) ;


    /**
     * 根据重点单位电话 获得重点单位信息
     * @return
     */
    KeyUnitBean findKeyUnitByPhone( String unitPhone ) ;



    /**
     * 根据重点单位id 获得重点单位信息
     * @return
     */
    KeyUnitBean findKeyUnitById( String keyUnitId ) ;

    /** 获得重点单位 id 与 重点单位Bean 对应关系的map
     * @return 返回结果
     */
    Map<String, KeyUnitBean> findKeyUnitBeanMap( List< String> keyUnitIds) ;

    /**
     * 根据预案id 获得重点单位信息
     * @return
     */
    KeyUnitBean findKeyUnitByPlanId( String planId ) ;

    /**
     *  根据经纬度 查询范围内重点单位id
     * @return 重点单位列表
     */
    List<String> findKeyUnitIncidentRange( String longitude , String latitude ) ;

    /**
     * 保存重点单位
     * @return 重点单位bean
     * */
    KeyUnitBean saveKeyUnit(KeyUnitSaveInputInfo inputInfo);

    /**
     * 删除重点单位
     * @return 重点单位bean
     * */
    Boolean deleteKeyUnit( List<String> keyUnitIds );




}
