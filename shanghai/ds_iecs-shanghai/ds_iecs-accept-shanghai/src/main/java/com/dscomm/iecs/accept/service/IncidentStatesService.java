package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.dal.po.IncidentStatesEntity;
import com.dscomm.iecs.accept.graphql.inputbean.IncidentStatesInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.IncidentStatesBean;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidentStatesService {

    /*
     * 查询案件的状态
     * */
    List<IncidentStatesBean> queryInfo();

    /*
     * 根据条件查询案件的状态
     * */
    List<IncidentStatesBean> queryInfoByInfo(String ztbm1);

    /*
     *根据案件状态1,修改案件状态2中有哪些状态可以显示
     * */
    /*Boolean updateInfo(IncidentStatesInputInfo info);*/
}
