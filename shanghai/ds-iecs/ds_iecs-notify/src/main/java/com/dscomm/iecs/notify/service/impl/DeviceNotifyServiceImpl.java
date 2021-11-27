package com.dscomm.iecs.notify.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.notify.eunms.FilterTypeDataEnum;
import com.dscomm.iecs.notify.service.DeviceNotifyService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mx.StringUtils;
import org.mx.comps.notify.client.NotifyBean;
import org.mx.comps.notify.online.OnlineDevice;
import org.mx.comps.notify.online.OnlineManager;
import org.mx.comps.notify.processor.NotifyProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 描述：终端（含PC或移动终端）警情实时推送服务实现
 *
 * @author john peng
 * Date time 2018/7/15 上午10:52
 */
@Component("deviceNotifyService")
public class DeviceNotifyServiceImpl implements DeviceNotifyService {
    private static final Log logger = LogFactory.getLog(DeviceNotifyServiceImpl.class);
    private NotifyProcessor notifyProcessor;
    private OnlineManager onlineManager;

    /**
     * 构造函数
     *
     * @param onlineManager   在线终端管理器
     * @param notifyProcessor 推送处理器
     */
    @Autowired
    public DeviceNotifyServiceImpl(OnlineManager onlineManager, NotifyProcessor notifyProcessor) {
        super();
        this.onlineManager = onlineManager;
        this.notifyProcessor = notifyProcessor;
    }

    /**
     * 向所有在线终端推送指定的业务数据
     *
     * @param messageId      消息号
     * @param messageVersion 消息版本
     * @param data           业务数据内容
     */
    private void pushData2AllDevices(String messageId, String messageVersion, JSONObject data) {
        Set<OnlineDevice> onlineDeviceSet = onlineManager.getOnlineDevices(null);
        StringBuilder sb = new StringBuilder();
        for (OnlineDevice device : onlineDeviceSet) {
            sb.append(device.getDeviceId());
            sb.append(",");
        }
        if (sb.length() <= 0) {
            if (logger.isWarnEnabled()) {
                logger.warn("There are not any online devices.");
            }
            return;
        }
        String tar = sb.substring(0, sb.length() - 1);
        NotifyBean<JSONObject> notify = new NotifyBean<>("system", "NA",
                NotifyBean.TarType.Devices, tar, -1, messageId, messageVersion, data);
        notifyProcessor.notifyProcess((JSONObject) JSON.toJSON(notify));
    }

    /**
     * {@inheritDoc}
     *
     * @see #pushNotifyMessage(String , String )
     */
    @Override
    public void  pushNotifyMessage(String code , String  body) {
        if (StringUtils.isBlank(code)) {
            if (logger.isWarnEnabled()) {
                logger.warn("the code  is blank.");
            }
        }
        // 默认 封装消息格式
        JSONObject data = new JSONObject();
        data.put("body", body );
        // 默认对所有在线用户进行推送
        pushData2AllDevices( code , "1.0", data  );
    }

    /**
     * {@inheritDoc}
     *
     * @see #pushNotifyMessage(String , String )
     */
    @Override
    public void pushNotifyMessageFilter  ( Integer filterType  , List<String> filterKeys , String code, String body) {
        if (StringUtils.isBlank(code)) {
            if (logger.isWarnEnabled()) {
                logger.warn("the code  is blank.");
            }
        }
        // 默认 封装消息格式
        JSONObject data = new JSONObject();
        data.put("body", body );
        // 默认对所有在线用户进行推送
        pushData2FilterDevices( filterType , filterKeys , code , "1.0", data  );
    }


    /**
     * 根据条件过滤 在线终端推送指定的业务数据
     * @param filterType      过滤类型
     * @param filterKeys      过滤条件
     * @param messageId      消息号
     * @param messageVersion 消息版本
     * @param data           业务数据内容
     */
    private void pushData2FilterDevices( Integer filterType  ,  List<String> filterKeys , String messageId, String messageVersion, JSONObject data) {

        Set<OnlineDevice> filterOnlineDeviceSet = new HashSet<>( );
        if (filterKeys != null && !filterKeys.isEmpty()) {
            Set<OnlineDevice> allOnlineDeviceSet = onlineManager.getOnlineDevices(null);
            for ( String  filterKey : filterKeys) {
                allOnlineDeviceSet.forEach( foreachKey  -> {
                    String  deviceId = foreachKey.getDeviceId() ;
                     //机构过滤  机构角色过滤
                     if(  ( FilterTypeDataEnum.FILTER_TYPE_DATA_ORGANIZATION.getCode() == filterType
                             || FilterTypeDataEnum.FILTER_TYPE_DATA_ORGANIZATION_ROLE.getCode() == filterType )
                             && deviceId.startsWith(  filterKey )  ){
                         filterOnlineDeviceSet.add( foreachKey ) ;

                    // 用户过滤
                     }else if( FilterTypeDataEnum.FILTER_TYPE_DATA_USER.getCode() == filterType
                             && deviceId.endsWith(   filterKey )   ) {
                         filterOnlineDeviceSet.add( foreachKey ) ;
                     // 如果不是 全字段模糊
                     }else if(  ( FilterTypeDataEnum.FILTER_TYPE_DATA_ORGANIZATION.getCode() != filterType
                             || FilterTypeDataEnum.FILTER_TYPE_DATA_ORGANIZATION_ROLE.getCode() != filterType
                             || FilterTypeDataEnum.FILTER_TYPE_DATA_USER.getCode() != filterType  )
                             && deviceId.contains( filterKey )  ) {
                         filterOnlineDeviceSet.add( foreachKey ) ;
                     }
                } );
            }
        }
        StringBuilder sb = new StringBuilder();
        for (OnlineDevice device : filterOnlineDeviceSet) {
            sb.append(device.getDeviceId());
            sb.append(",");
        }
        if (sb.length() <= 0) {
            if (logger.isWarnEnabled()) {
                logger.warn("There are not any online devices.");
            }
            return;
        }
        String tar = sb.substring(0, sb.length() - 1);
        NotifyBean<JSONObject> notify = new NotifyBean<>("system", "NA",
                NotifyBean.TarType.Devices, tar, -1, messageId, messageVersion, data);
        notifyProcessor.notifyProcess((JSONObject) JSON.toJSON(notify));
    }

}
