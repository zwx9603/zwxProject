package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.SecurityHintsEntity;
import com.dscomm.iecs.accept.dal.po.SecurityHintsReceiverEntity;
import com.dscomm.iecs.accept.graphql.inputbean.SecurityInputInfo;
import com.dscomm.iecs.accept.graphql.inputbean.SecurityReceiverInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.SecurityBean;
import com.dscomm.iecs.accept.graphql.typebean.SecurityReceiverBean;

public class SecurityTransformUtil {


    /**
     *特别警示输入信息转entity
     *
     **/
    public static SecurityHintsEntity transform(SecurityInputInfo inputInfo ){
        if ( inputInfo != null){
            SecurityHintsEntity entity = new SecurityHintsEntity();
            entity.setIncidentId(inputInfo.getIncidentId());
            entity.setCommandId(inputInfo.getCommandId());
            entity.setTitle(inputInfo.getTitle());
            entity.setContent(inputInfo.getContent());
            entity.setEffectiveTime(inputInfo.getEffectiveTime());
            entity.setSenderName(inputInfo.getSenderName());
            entity.setWriterId(inputInfo.getWriterId());
            entity.setRemarks(inputInfo.getRemark());
            return   entity ;
        }
        return null ;
    }


    public static SecurityHintsReceiverEntity transform(SecurityReceiverInputInfo inputInfo){
        if (inputInfo!= null){
            SecurityHintsReceiverEntity entity = new SecurityHintsReceiverEntity();
            entity.setReceiverId(inputInfo.getReceiverId());
            entity.setReceiverType(inputInfo.getReceiverType());
            entity.setRemarks( inputInfo.getRemarks() );
            return  entity ;
        }
        return null ;
    }


    /**
     *特别警示转换
     * @param
     * @return
     **/
     public static SecurityBean transform(SecurityHintsEntity entity){
         if (entity != null && entity.isValid()){
             SecurityBean bean = new SecurityBean();
             bean.setId(entity.getId());
             bean.setIncidentId(entity.getIncidentId());
             bean.setCommandId(entity.getCommandId());
             bean.setTitle(entity.getTitle());
             bean.setContent(entity.getContent());
             bean.setEffectiveTime(entity.getEffectiveTime());
             bean.setNotificationTime( entity.getNotificationTime() );
             bean.setSenderName(entity.getSenderName());
             bean.setWriterId(entity.getWriterId());
             bean.setRemarks(entity.getRemarks());
             return  bean ;
         }
         return null ;
     }

     /**
      * 特别警示发送记录转换
      * */
     public static SecurityReceiverBean transform (SecurityHintsReceiverEntity entity){
         SecurityReceiverBean bean = new SecurityReceiverBean();
         if (entity != null && entity.isValid()){
             bean.setId(entity.getId());
             bean.setIncidentId(entity.getIncidentId());
             bean.setCommandId(entity.getCommandId());
             bean.setSecurityHintsId(entity.getSecurityHintsId());
             bean.setNotificationTime(entity.getNotificationTime());
             bean.setReceiverId(entity.getReceiverId());
             bean.setReceiverType(entity.getReceiverType());
             bean.setRemarks( entity.getRemarks() );
             return  bean ;
         }
         return bean;
     }





}
