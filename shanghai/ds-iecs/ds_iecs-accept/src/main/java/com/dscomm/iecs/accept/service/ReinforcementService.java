package com.dscomm.iecs.accept.service;


import com.dscomm.iecs.accept.graphql.inputbean.ReinforcementAskSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.ReinforcementAskBean;


/**
 * 描述：增援 服务
 */
public interface ReinforcementService {

    /**
     * 保存请求增援 (   )
     *
     * @param inputInfo 请求增援参数
     * @return 保存结果
     */
    ReinforcementAskBean saveReinforcementAsk(ReinforcementAskSaveInputInfo inputInfo);



}
