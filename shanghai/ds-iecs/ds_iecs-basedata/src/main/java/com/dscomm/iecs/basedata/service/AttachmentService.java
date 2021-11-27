package com.dscomm.iecs.basedata.service;




import com.dscomm.iecs.basedata.graphql.inputbean.AttachmentSaveInputInfo;
import com.dscomm.iecs.basedata.graphql.typebean.AttachmentBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：附件信息 服务
 */
public interface AttachmentService {

    /**
     * 根据 警情id 关联id  获得 附件信息
     */
    List<AttachmentBean> findAttachment(String incidentId , String relationId ) ;

    /**
     * 根据   关联id  关联对象 获得 附件信息
     */
    Map<String , List<AttachmentBean> > findAttachmentByRelationIds (List<String> relationIds , String relationObject  ) ;

    /**
     * 保存 附件信息
     */
    AttachmentBean saveAttachment( AttachmentSaveInputInfo queryBean ) ;


    /**
     * 保存附件列表
     *
     * @param queryBean  保存的附件信息列表
     * @return 保存后的附件列表信息
     */
    List<AttachmentBean> saveAttachmentList(List<AttachmentSaveInputInfo> queryBean);


    /**
     * 根据id删除附件
     *
     * @param id 附件id
     * @return 删除的附件信息
     */
    AttachmentBean deleteAttachmentById(String id);
}
