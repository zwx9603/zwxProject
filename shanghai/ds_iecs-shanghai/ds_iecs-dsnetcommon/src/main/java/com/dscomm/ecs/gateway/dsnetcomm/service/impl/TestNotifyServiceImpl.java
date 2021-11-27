package com.dscomm.ecs.gateway.dsnetcomm.service.impl;

import com.dscomm.ecs.gateway.dsnetcomm.service.DsNetCommonManger;
import com.dscomm.ecs.gateway.dsnetcomm.service.TestNotifyService;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.SendMessageBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author YangFuxi
 * Date Time 2020/8/28 11:34
 */
@Component
public class TestNotifyServiceImpl implements TestNotifyService {
    private static final Logger logger=LoggerFactory.getLogger(TestNotifyServiceImpl.class);

    private DsNetCommonManger manger;

    @Autowired
    public TestNotifyServiceImpl(DsNetCommonManger manger) {
        this.manger = manger;
    }


    @Override
    public Boolean sendMessage(SendMessageBO bo) {
        try {
            return manger.sendMessage(Integer.parseInt(bo.getDstType().substring(2),16),Integer.parseInt(bo.getDstId()),Integer.parseInt(bo.getTransType()),Integer.parseInt(bo.getMsgId().substring(2),16),Integer.parseInt(bo.getMsgId().substring(2),16),bo.getData());
        }catch (Exception ex){
            ex.printStackTrace();
            return false ;
        }
    }
}
