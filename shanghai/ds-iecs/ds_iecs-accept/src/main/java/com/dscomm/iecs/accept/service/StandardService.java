package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.StandardQueryInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.UpdateStandardInfo;
import com.dscomm.iecs.accept.graphql.typebean.StandardBean;
import com.dscomm.iecs.accept.graphql.typebean.UpdateStandardBean;

import java.util.List;

/**
 * 描述：规范用语
 */
public interface StandardService {
    List<StandardBean> findStandard(StandardQueryInputInfo queryBean);

    UpdateStandardBean saveOrUpdateStandard(UpdateStandardInfo queryBean);
}
