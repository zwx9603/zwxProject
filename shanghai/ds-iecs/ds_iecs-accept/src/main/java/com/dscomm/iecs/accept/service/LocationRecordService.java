package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.LocationRecordInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.LocationRecordBean;

import java.util.List;

public interface LocationRecordService {

    /**
     * 保存定位记录
     *
     * */
    LocationRecordBean saveLocationRecord(LocationRecordInputInfo inputInfo);

    /**
     * 根据警情id查询定位记录
     *
     * */
    List<LocationRecordBean> getLocationRecord(String incidentId);
}
