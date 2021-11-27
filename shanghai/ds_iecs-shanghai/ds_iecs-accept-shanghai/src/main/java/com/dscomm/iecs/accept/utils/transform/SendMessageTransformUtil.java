package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.SendMessageEntity;
import com.dscomm.iecs.accept.graphql.typebean.GradeBean;
import com.dscomm.iecs.accept.graphql.typebean.SendMessageBean;

import java.util.ArrayList;
import java.util.List;

public class SendMessageTransformUtil {
    public static List<SendMessageBean> transform(List<SendMessageEntity> list){
        List<SendMessageBean> list1 = new ArrayList<>();
        for (SendMessageEntity ele:list) {
            SendMessageBean sendMessageBean = new SendMessageBean();
            sendMessageBean.setId(ele.getId());
            sendMessageBean.setDhhm(ele.getDhhm());
            sendMessageBean.setIssend(ele.getIssend());
            sendMessageBean.setLdmc(ele.getLdmc());
            sendMessageBean.setSsjgbh(ele.getSsjgbh());
            sendMessageBean.setRyid(ele.getRyid());
            list1.add(sendMessageBean);
        }
        return list1;
    }
}
