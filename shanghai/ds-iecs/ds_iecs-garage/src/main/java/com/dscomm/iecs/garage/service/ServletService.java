package com.dscomm.iecs.garage.service;

import java.util.Date;

/**
 * 描述: 服务器 服务类
 *
 */
public interface ServletService {


    /**
     * 获取系统时间
     *
     * @return 返回统一时时间
     */
    Long getSystemTime();

    /**
     * 获取系统时间
     * @return 返回统一时时间
     */
    Date getSystemTimeFormat();


}
