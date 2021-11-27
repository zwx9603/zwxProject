package com.dscomm.iecs.basedata.service;



import com.dscomm.iecs.basedata.graphql.typebean.DangerousChemicalsBean;

import java.util.List;

/**
 * @author: zhangsheng
 * @Date: 2020/4/5 16:11
 * @describe:
 */
public interface DangerousChemicalsService {


    /**
     * 危化品列表
     * @param keyword
     * @return
     */
    List<DangerousChemicalsBean> getDangerousChemicalsList(String keyword);
}
