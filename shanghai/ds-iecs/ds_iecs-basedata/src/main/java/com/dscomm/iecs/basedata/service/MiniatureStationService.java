package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.base.graphql.typebean.PaginationBean;
import com.dscomm.iecs.basedata.graphql.inputbean.MiniatureStationQueryInputInfo;
import com.dscomm.iecs.basedata.graphql.inputbean.MiniatureStationSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.MiniatureStationBean;

import java.util.List;

/**
 * 微型消防站服务类
 */
public interface MiniatureStationService {


    /**
     * 根据坐标查范围内的 微站
     * @param radius 范围半径 米
     * @return 联动单位列表
     */
    List<MiniatureStationBean> findMiniatureStationRange(String longitude , String latitude , String radius );

    /**
     *  保存修改微型消防站信息
     * @param inputInfo
     * @return
     */
    MiniatureStationBean saveMiniatureStation(MiniatureStationSaveInputInfo inputInfo) ;

    /**
     *   微型消防站 更新经纬度 地址信息
     * @param inputInfo
     * @return
     */
    MiniatureStationBean saveMiniatureStationLocation(MiniatureStationSaveInputInfo inputInfo) ;


    /**
     *  根据 id 集合移除机构信息
     * @param ids
     * @return
     */
    Boolean removeMiniatureStation(List<String> ids) ;

    /**
     * 根据条件 查询微站信息
     * @return
     */
    PaginationBean<MiniatureStationBean> findMiniatureStationCondition( MiniatureStationQueryInputInfo inputInfo ) ;


    /**
     * 根据微站id 查询微站详情
     * @return
     */
    MiniatureStationBean findMiniatureStation( String miniatureStationId ) ;





}
