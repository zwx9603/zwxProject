package com.dscomm.iecs.accept.service;

import com.dscomm.iecs.accept.dal.po.SendMessageEntity;
import com.dscomm.iecs.accept.graphql.inputbean.SendMessageInputInfo;
import com.dscomm.iecs.accept.graphql.typebean.SendMessageBean;

import java.util.List;

public interface SendMessageService {
    /*
     *做全查询操作
     * */
    List<SendMessageBean> queryAll();

    /**
     * 根据被发送人姓名或机构名称做条件查询
     * @param ldmc
     * @param ssjgbh
     * @return
     */
    List<SendMessageBean> queryByInfo(String ldmc, String ssjgbh);

    /*
    * 添加信息
    * */
    Boolean addInfo(SendMessageInputInfo info);

    /**
     * 根据人员id做批量删除
     * @param
     * @return
     */
    Boolean deleteByInfo(List<String> list);

    /**
     * 根据人员id和人员所属机构批量修改手机号、是否发送信息
     * @return
     */
    Boolean updateInfo(SendMessageInputInfo info);
}
