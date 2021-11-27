package com.dscomm.iecs.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.base.graphql.typebean.NotifyMessageBean;
import com.dscomm.iecs.base.graphql.typebean.ReceiverMessageBean;
import com.dscomm.iecs.base.service.NotifyClientService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Strings;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.service.client.rest.RestInvokeException;
import org.mx.service.rest.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 描述：notify 推送服务实现
 *
 * @author john peng
 * Date time 2018/7/15 上午10:52
 */
@Component("notifyClientServiceImpl")
public class NotifyClientServiceImpl implements NotifyClientService {
    private static final Log logger = LogFactory.getLog(NotifyClientServiceImpl.class);
    private RestClientInvoke restClientInvoke = null;

    @Value("${notify.enable:false}")
    private String  notifyEnable  ;
    @Value("${notify.url:http://127.0.0.1:19999}")
    private String notifyUrl;

    /**
     * 构造函数
     *
     */
    @Autowired
    public NotifyClientServiceImpl( ) {
        super();
        this.restClientInvoke = new RestClientInvoke();
    }

    static final  Integer  FILTER_TYPE_DATA_ORGANIZATION = 1 ; // "机构过滤"

    static final  Integer  FILTER_TYPE_DATA_ORGANIZATION_ROLE = 6; //  "机构角色过滤"

    static final  Integer  FILTER_TYPE_DATA_USER  =  9; //  "用户过滤"




    @Override
    public void pushMessageToUsers(String code, Object body, List<String> receivers) {
        if( receivers != null && receivers.size() > 0 ){
            List<String> filterKeys = receivers  ;
            pushNotifyMessageFilter( FILTER_TYPE_DATA_USER , filterKeys , code , JSONObject.toJSONString(body)   ) ;
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see #pushMessage(String, Object, List)
     */
    @Override
    public void pushMessage(String code, Object body, List<ReceiverMessageBean> receivers) {
        if( receivers != null && receivers.size() > 0 ){
            List<String> filterKeys = new ArrayList<>()  ;
            for( ReceiverMessageBean receiver : receivers ){
                filterKeys.add( receiver.getId() ) ;
            }
            pushNotifyMessageFilter( FILTER_TYPE_DATA_USER , filterKeys , code , JSONObject.toJSONString(body)   ) ;
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #pushMessage(String, Object, String, List)
     */
    @Override
    public void pushMessage(String code, Object body, String fromUserCode, List<ReceiverMessageBean> receivers) {
        if( receivers != null && receivers.size() > 0 ){
            List<String> filterKeys = new ArrayList<>()  ;
            for( ReceiverMessageBean receiver : receivers ){
                filterKeys.add( receiver.getId() ) ;
            }
            pushNotifyMessageFilter( FILTER_TYPE_DATA_USER , filterKeys , code , JSONObject.toJSONString(body)   ) ;
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToDefaultSystemBusinessOrg(String, Object, Set)
     */
    @Override
    public void pushMessageToDefaultSystemBusinessOrg(String code, Object body, Set<String> orgs) {
        if( orgs != null && orgs.size() > 0 ){
            List<String> filterKeys = new ArrayList<>()  ;
            for( String receiver   : orgs ){
                filterKeys.add( receiver  ) ;
            }
            pushNotifyMessageFilter( FILTER_TYPE_DATA_ORGANIZATION , filterKeys , code , JSONObject.toJSONString(body)   ) ;
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #pushMessageToDefaultSystemBusinessOrgRole(String, Object, Set , Set )
     */
    @Override
    public void pushMessageToDefaultSystemBusinessOrgRole(String code, Object body, Set<String> orgs , Set<String> roles ) {
        if( orgs != null && orgs.size() > 0 && roles != null && roles.size() > 0  ){
            List<String> filterKeys = new ArrayList<>()  ;
            for( String org   : orgs ){
                for( String role    : roles ){
                    String receiver = org + "-" + role ;
                    filterKeys.add( receiver  ) ;
                }
            }
            pushNotifyMessageFilter( FILTER_TYPE_DATA_ORGANIZATION_ROLE , filterKeys , code , JSONObject.toJSONString(body)   ) ;
        }
    }


    private  void  pushNotifyMessage(  String code , String  body ) {
        //启用 notify  发送消息
        Boolean webSocketEnablebl = Boolean.parseBoolean( notifyEnable ) ;
        if(  webSocketEnablebl ){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    NotifyMessageBean vo = new NotifyMessageBean();
                    vo.setCode( code );
                    vo.setBody( body );
                    try {
                        restClientInvoke.post(String.format("%s/rest/iecs/v1.0/notify/message", notifyUrl),
                                vo, DataVO.class);
                    } catch (RestInvokeException ex) {
                        if (logger.isErrorEnabled()) {
                            logger.error(String.format("Invoke the %s restful service fail.", notifyUrl), ex);
                        }
                    }catch ( Exception ex) {
                        if (logger.isErrorEnabled()) {
                            logger.error(String.format("Invoke the %s restful service fail.", notifyUrl), ex);
                        }
                    }
                }
            });
            thread.start();


        }
    }



    private  void  pushNotifyMessageFilter( Integer filterType  , List<String> filterKeys , String code , String  body ) {
        //启用 notify  发送消息
        Boolean webSocketEnablebl = Boolean.parseBoolean( notifyEnable ) ;
        if(  webSocketEnablebl ){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    NotifyMessageBean vo = new NotifyMessageBean();
                    vo.setFilterType( filterType );
                    vo.setFilterKeys( filterKeys );
                    vo.setCode( code );
                    vo.setBody( body );
                    try {
                        restClientInvoke.post(String.format("%s/rest/iecs/v1.0/notify/messageFilter", notifyUrl),
                                vo, DataVO.class);
                    } catch (RestInvokeException ex) {
                        if (logger.isErrorEnabled()) {
                            logger.error(String.format("Invoke the %s restful service fail.", notifyUrl), ex);
                        }
                    }catch ( Exception ex) {
                        if (logger.isErrorEnabled()) {
                            logger.error(String.format("Invoke the %s restful service fail.", notifyUrl), ex);
                        }
                    }
                }
            });
            thread.start();
        }
    }


}
