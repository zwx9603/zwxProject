package com.dscomm.iecs.garage.service;

/**
 * 描述： UDP监控方式
 *
 * @author YangFuXi Date Time 2018/11/29 16:04
 */
public interface UdpService {
    /**
     * 同步UDP监控到的移动视频数据
     * @param message 要处理的数据
     */
    void transport(String message);
}
