package com.dscomm.iecs.schedule.service.hangzhou;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface PushDhService {


    /**
     * oa 账号信息
     * @return
     */
    List<JSONObject> PushDh() ;

    /**
     * 消防机构信息
     * @return
     */
    List<JSONObject> PushDh2() ;
}
