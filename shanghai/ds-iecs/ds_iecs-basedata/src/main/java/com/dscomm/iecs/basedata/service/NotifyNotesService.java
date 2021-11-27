package com.dscomm.iecs.basedata.service;

/**
 * 描述:通知其他节点服务
 *
 * @author YangFuxi
 * Date Time 2020/3/12 15:15
 */
public interface NotifyNotesService {
    /**
     * 通知其他节点更新服务
     *
     * @param path 路径
     * @param obj  要更新的数据
     */
    void notifyNodes(String path, Boolean transmit, Object obj);



}
