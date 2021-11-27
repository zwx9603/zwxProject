package com.dscomm.iecs.notify.websocket;

import com.alibaba.fastjson.JSONObject;
import org.mx.comps.notify.processor.NotifyProcessListener;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 描述： 消防实时消息推送监听接口实现
 *
 * @author john peng
 * Date time 2018/7/15 上午11:33
 */
@Component("iecsNotifyProcessListener")
public class IecsNotifyProcessListener implements NotifyProcessListener {
    /**
     * {@inheritDoc}
     *
     * @see NotifyProcessListener#before(JSONObject)
     */
    @Override
    public void before(JSONObject data) {
        // TODO
    }

    /**
     * {@inheritDoc}
     *
     * @see NotifyProcessListener#after(JSONObject, boolean, Set)
     */
    @Override
    public void after(JSONObject data, boolean success, Set<String> invalidDevices) {
        // TODO
    }
}
