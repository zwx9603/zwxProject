package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.graphql.inputbean.ImportantReminderInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.ImportantReminderBean;
import com.dscomm.iecs.base.graphql.typebean.PaginationBean;

/**
 * 要事提醒服务类
 *
 * */
public interface ImportantReminderService {

    /**
     * 保存要事提醒
     * @param importantReminderInputInfo 要事详情输入信息
     * @return boolean
     * */
    Boolean saveImportantReminder(ImportantReminderInputInfo importantReminderInputInfo);


    /**
     * 要事提醒详情
     * @param id 要事提醒id
     * @return 要事提醒信息
     * */
    ImportantReminderBean importantReminderDetail(String id);

    /**
     * 删除要事提醒
     * @param id 要事提醒id
     * @return Boolean
     * */
    Boolean deleteImportantReminder(String id);

    /**
     *要事提醒列表
     * @param importantReminderInputInfo 要事查询输入信息
     * @param pageSize 分页信息
     * @param pageNum
     * @return ImportantReminderBean列表
     * */
    PaginationBean<ImportantReminderBean> listImportantReminder(ImportantReminderInputInfo importantReminderInputInfo, Integer pageSize, Integer pageNum);

}
