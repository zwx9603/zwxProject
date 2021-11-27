package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.accept.graphql.inputbean.FireDzInputBean;
import com.dscomm.iecs.accept.graphql.typebean.FireDzBean;

import java.util.List;
import java.util.Map;

public interface VehicleDispatchingService {

    /*
    * 火灾到中队的距离
    * */
    Map<String,List<FireDzBean>> queryJL(
            List<FireDzInputBean> list
//            FireDzInputBean fireDzInputBean
    );

    /*
    * 案发点到周围驻防点的距离及可派车辆
    * */
    List<FireDzBean> findZfdjl(List<FireDzInputBean> list);
}
