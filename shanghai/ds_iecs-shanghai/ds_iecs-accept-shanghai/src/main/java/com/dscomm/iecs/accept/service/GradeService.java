package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.dal.po.GradeEntity;
import com.dscomm.iecs.accept.graphql.typebean.GradeBean;

import java.util.List;

/*
 * @author Zhao Wenxue
 * */
public interface GradeService {
    /*
     * 根据参数案件类型、案件等级 、处置对象查询
     * @author Zhao Wenxue
     * */
    List<GradeBean> queryInfo(String czdx, String ajdj, String ajlx);
}
