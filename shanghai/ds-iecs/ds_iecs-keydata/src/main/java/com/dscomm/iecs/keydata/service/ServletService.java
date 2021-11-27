package com.dscomm.iecs.keydata.service;

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
    void updateSystemTime(  );


    /**
     * 获取系统时间
     *
     * @return 返回统一时时间
     */
    Long getSystemTime(  );

    /**
     * 获取系统时间
     * @return 返回统一时时间
     */
    Date getSystemTimeFormat(   );


    //TODO 后期废除 临时解决方法
    /**
     * 获取系统时间 指定类型时间信息
     *
     * @return 返回统一时时间
     */
    Long getSystemTime( Integer type  );


    /**
     * 获取系统时间
     * @return 返回统一时时间
     */
    Date getSystemTimeFormat( Integer type  );

}
