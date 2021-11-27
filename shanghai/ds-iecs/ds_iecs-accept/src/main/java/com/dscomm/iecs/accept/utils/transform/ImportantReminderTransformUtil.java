package com.dscomm.iecs.accept.utils.transform;

import com.dscomm.iecs.accept.dal.po.ImportantReminderEntity;
import com.dscomm.iecs.accept.graphql.inputbean.ImportantReminderInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.ImportantReminderBean;

/**
 * 要事提醒inputInfo，Entity，Bean转换方法
 *
 * */
public class ImportantReminderTransformUtil {

    /**inputinfo转entity*/
    public static ImportantReminderEntity transformEntity(ImportantReminderInputInfo inputInfo){
        if( inputInfo != null ){
            ImportantReminderEntity importantReminderEntity = new ImportantReminderEntity();
            importantReminderEntity.setId(inputInfo.getId());
            importantReminderEntity.setPersonId(inputInfo.getPersonId());
            importantReminderEntity.setPersonName(inputInfo.getPersonName());
            importantReminderEntity.setPersonUnitId(inputInfo.getPersonUnitId());
            importantReminderEntity.setPersonUnitName(inputInfo.getPersonUnitName());
            importantReminderEntity.setTitle(inputInfo.getTitle());
            importantReminderEntity.setContext(inputInfo.getContext());
            importantReminderEntity.setReleaseTime(inputInfo.getReleaseTime());
            importantReminderEntity.setRemarks(inputInfo.getRemarks());
            importantReminderEntity.setBeginTime(inputInfo.getBeginTime());
            importantReminderEntity.setEndTime(inputInfo.getEndTime());
            return  importantReminderEntity ;
        }
        return  null ;
    }

    /**entity转bean*/
    public static ImportantReminderBean transformBean(ImportantReminderEntity entity){
        if( entity != null ){
            ImportantReminderBean importantReminderBean = new ImportantReminderBean();
            importantReminderBean.setId(entity.getId());
            importantReminderBean.setPersonId(entity.getPersonId());
            importantReminderBean.setPersonName(entity.getPersonName());
            importantReminderBean.setPersonUnitId(entity.getPersonUnitId());
            importantReminderBean.setPersonUnitName(entity.getPersonUnitName());
            importantReminderBean.setTitle(entity.getTitle());
            importantReminderBean.setContext(entity.getContext());
            importantReminderBean.setReleaseTime(entity.getReleaseTime());
            importantReminderBean.setRemarks(entity.getRemarks());
            importantReminderBean.setBeginTime(entity.getBeginTime());
            importantReminderBean.setEndTime(entity.getEndTime());
            return  importantReminderBean ;
        }
        return  null ;
    }



}
