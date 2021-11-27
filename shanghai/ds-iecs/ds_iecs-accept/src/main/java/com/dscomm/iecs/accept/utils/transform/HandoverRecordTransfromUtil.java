package com.dscomm.iecs.accept.utils.transform;


import com.dscomm.iecs.accept.dal.po.HandoverRecordEntity;
import com.dscomm.iecs.accept.graphql.inputbean.HandoverRecordSaveInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.HandoverRecordBean;
import com.dscomm.iecs.basedata.graphql.typebean.user.UserInfo;

/**
 *交接班日志 转换工具
 * */
public class HandoverRecordTransfromUtil {

    public static HandoverRecordBean transform(HandoverRecordEntity entity){
        HandoverRecordBean bean = new HandoverRecordBean();
        if (entity != null){
            bean.setId(entity.getId());
            bean.setDeleted(entity.getDeleted());
            bean.setHandoverContent(entity.getHandoverContent());
            bean.setHandoverOrganizationId(entity.getHandoverOrganizationId());
            bean.setHandoverPersonId(entity.getHandoverPersonId());
            bean.setHandoverPersonName(entity.getHandoverPersonName());
            bean.setHandoverPersonNumber(entity.getHandoverPersonNumber());
            bean.setHandoverSeatNumber(entity.getHandoverSeatNumber());
            bean.setHandoverTime(entity.getHandoverTime());
            bean.setSJC(entity.getSJC());
        }
        return bean;
    }

    public static HandoverRecordEntity transfrom(HandoverRecordSaveInputInfo inputInfo, UserInfo userInfo){
        HandoverRecordEntity entity = new HandoverRecordEntity();
        if (inputInfo != null){
            entity.setId(inputInfo.getId());
            entity.setHandoverContent(inputInfo.getHandoverContent());
            entity.setHandoverPersonId(inputInfo.getHandoverPersonId());
            entity.setHandoverPersonName(inputInfo.getHandoverPersonName());
            entity.setHandoverOrganizationId(userInfo.getOrgId());
            entity.setHandoverPersonNumber(userInfo.getAccount());
            entity.setHandoverSeatNumber(userInfo.getAgentNum());
            entity.setHandoverTime(inputInfo.getHandoverTime());
            entity.setSJC(inputInfo.getHandoverTime());
        }
        return entity;
    }

}
