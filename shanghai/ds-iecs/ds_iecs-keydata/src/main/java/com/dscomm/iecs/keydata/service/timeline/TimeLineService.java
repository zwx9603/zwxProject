package com.dscomm.iecs.keydata.service.timeline;


import com.dscomm.iecs.keydata.service.timeline.bo.EsTimelineRequestBO;

/**
 * 描述:时间轴服务
 *
 */
public interface TimeLineService {
    /**
     * 保存时间轴服务
     * @param esTLineRequestBO 时间轴内容
     * @return 返回保存结果
     */
    Boolean saveTimeline(EsTimelineRequestBO esTLineRequestBO);
}
