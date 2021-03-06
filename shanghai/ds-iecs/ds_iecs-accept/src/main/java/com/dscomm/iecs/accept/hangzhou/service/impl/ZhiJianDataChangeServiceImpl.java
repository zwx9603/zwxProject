package com.dscomm.iecs.accept.hangzhou.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.accept.dal.po.IncidentExtend119Entity;
import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.hangzhou.service.ZhiJianDataChangeService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.VehicleService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("zhiJianDataChangeServiceImpl")
public class ZhiJianDataChangeServiceImpl implements ZhiJianDataChangeService {

    private static final Logger logger = LoggerFactory.getLogger(ZhiJianDataChangeServiceImpl.class);
    private LogService logService;
    private Environment env;
    private GeneralAccessor accessor ;
    private ServletService servletService ;
    private VehicleService vehicleService ;

    private Boolean whetherZhiJian  = false ; //???????????????????????????
    private String host  ;// ??????????????????
    private String accessKey ;
    private String secretKey ;


    @Autowired
    public ZhiJianDataChangeServiceImpl(LogService logService , @Qualifier("generalAccessor") GeneralAccessor accessor,
                                        Environment env , ServletService servletService , VehicleService vehicleService
    ) {
        this.logService = logService;
        this.env = env ;
        this.accessor = accessor ;
        this.servletService = servletService ;
        this.vehicleService  = vehicleService ;

        whetherZhiJian = Boolean.parseBoolean(env.getProperty("zhiJian.enable"));
        host = env.getProperty("zhiJianUrl");
        accessKey = env.getProperty("zhiJianAccessKey");
        secretKey = env.getProperty("zhiJianSecretKey");



    }

    /**
     * ??????????????????????????? ???????????????????????????????????????????????????????????????
     */
    @Transactional
    @Override
    public Boolean newCase(IncidentBean incidentBean) {
        logService.infoLog(logger, "service", "newCase", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (  !whetherZhiJian ){
            return false;
        }

        List<String> otherParams = new ArrayList<String>();
        otherParams.add("eventTitle=" +  incidentBean.getCrimeAddress() );//?????? ???????????? Required
        otherParams.add("eventDesc=" + incidentBean.getIncidentDescribe() ); //?????? ??????
        otherParams.add("eventType=" + incidentBean.getIncidentTypeCode() ); // ????????????,1:?????????2: ???????????????3:???????????? Required
        otherParams.add("eventLevel=" + incidentBean.getIncidentLevelCode() );//??????,1???5 Required
        otherParams.add("eventPlace=" + incidentBean.getCrimeAddress() );//?????????????????????????????????????????????????????? Required
        otherParams.add("eventLocation=" + incidentBean.getIncidentLevelCode() );//?????????????????????????????? Required
        otherParams.add("eventLocationDetail=" + incidentBean.getCrimeAddress() );//???????????????????????????????????????
        otherParams.add("eventLocationCoordinateType=" + "wgs84" );//?????????????????????bd09,wgs84,gcj02 Required
        String longitude = incidentBean.getLongitude() ;
        if( Strings.isBlank( longitude ) ){
            longitude = "0.0" ;
        }
        String latitude = incidentBean.getLatitude() ;
        if( Strings.isBlank( latitude ) ){
            latitude = "0.0" ;
        }
        otherParams.add("eventLocationCoordinate=" +
                latitude + "," +  longitude );//???????????????????????????lat,lng Required
        otherParams.add("eventAlarmManPhone=" + incidentBean.getAlarmPersonPhone()  );//???????????????
        otherParams.add("enterpriseId=" + ""  );//????????????ID
//        othersParams.add("dispatchOrgIds=" + ""  );//????????????ID???????????????
//        othersParams.add("dispatchOrgNames=" + ""  );//????????????????????????????????????????????????????????????????????????
//        othersParams.add("dispatchPlateNumbers=" + ""  );//??????????????????????????????
        otherParams.add("dataSource=" + incidentBean.getId()  );//????????????ID  Required

        String  methodUrl = "/fight/create" ;

        //1?????????????????????????????????????????????accessKey???timestamp???nonce???
        List<String> params = new ArrayList<String>();
        params.add("page=1");//??????????????????
        params.add("accessKey="+accessKey);
        params.add("timestamp="+ servletService.getSystemTime() );
        params.add("nonce="+ Math.random());//GUID.random()??????????????????????????????????????????

        if( otherParams != null && otherParams.size() > 0 ){
            params.addAll( otherParams ) ;
        }

        params.sort(String::compareTo);

        //2??????&??????????????????????????????
        String paramsStr = params.stream().collect(Collectors.joining("&"));

        //3??????????????????????????????????????? secretKey
        String unsigned = paramsStr + "&secretKey=" + secretKey;

        //4?????????md5???
        String sign= DigestUtils.md5Hex( unsigned  ) ;

        //5??????????????????md5?????????????????????accessKey???timestamp???nonce????????????
        String data= paramsStr + "&sign="+sign;

        //6???????????????
        String url = host + methodUrl;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpPost post = new HttpPost(url);
                logService.infoLog(logger, "service", "sendHttp url ", url  );
                logService.infoLog(logger, "service", "sendHttp params  data ", data  );
                logService.writeLog(logger, "service", "sendHttp params  path ",url + "?" + data  );
                try {
                    StringEntity entity = new StringEntity(data, Charset.forName("UTF-8"));
                    entity.setContentEncoding("utf-8");
                    entity.setContentType("application/x-www-form-urlencoded");
                    post.setEntity(entity);
                    HttpClient client = wrapClient( url ) ;
                    HttpResponse response = client.execute(post);
                    HttpEntity responseEntity = response.getEntity();
                    String  result = EntityUtils.toString(responseEntity, "utf-8");
                    logService.writeLog(logger, "service", "sendHttp result ", result  );

                    //??????????????????
                    JSONObject resultJson = JSON.parseObject( result ) ;
                    String httpStatusCode =   resultJson.get( "code" ).toString() ;
                    //00000 ????????????
                    if( "00000".equals( httpStatusCode ) ){
                        String body =       resultJson.get( "body" ).toString() ;
                        JSONObject bodyJson = JSON.parseObject( body ) ;
                        String eventId =   bodyJson.get( "eventId" ).toString() ;
                        if( Strings.isNotBlank( eventId )  ){
                            IncidentExtend119Entity incidentExtend119Entity = accessor.getById(  incidentBean.getId() ,IncidentExtend119Entity.class ) ;
                            if( incidentExtend119Entity == null ){
                                incidentExtend119Entity = new IncidentExtend119Entity() ;
                                incidentExtend119Entity.setId(incidentBean.getId() );
                            }
                            incidentExtend119Entity.setOtherIncidentIdOne( eventId ); //??????????????????id
                            accessor.save( incidentExtend119Entity ) ;
                        }
                    }

                } catch (Exception ex) {
                    logService.erorLog(logger, "service", "sendHttp", String.format(" send zhi jian data fail   " ) ,ex  );
                }finally {
                    if (post != null){
                        post.releaseConnection();
                    }

                }

            }
        });
        thread.start();

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "newCOCCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }

    @Transactional
    @Override
    public Boolean modifyCase(IncidentBean incidentBean) {

        logService.infoLog(logger, "service", "modifyCase", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (  !whetherZhiJian ){
            return false;
        }


        List<String> otherParams = new ArrayList<String>();
        otherParams.add("eventTitle=" +  incidentBean.getCrimeAddress() );//?????? ???????????? Required
        otherParams.add("eventDesc=" + incidentBean.getIncidentDescribe() ); //?????? ??????
        otherParams.add("eventType=" + incidentBean.getIncidentTypeCode() ); // ????????????,1:?????????2: ???????????????3:???????????? Required
        otherParams.add("eventLevel=" + incidentBean.getIncidentLevelCode() );//??????,1???5 Required
        otherParams.add("eventPlace=" + incidentBean.getCrimeAddress() );//?????????????????????????????????????????????????????? Required
        otherParams.add("eventLocation=" + incidentBean.getCrimeAddress() );//?????????????????????????????? Required
        otherParams.add("eventLocationDetail=" + incidentBean.getCrimeAddress() );//???????????????????????????????????????
        otherParams.add("eventLocationCoordinateType=" + "wgs84" );//?????????????????????bd09,wgs84,gcj02 Required
        String longitude = incidentBean.getLongitude() ;
        if( Strings.isBlank( longitude ) ){
            longitude = "0.0" ;
        }
        String latitude = incidentBean.getLatitude() ;
        if( Strings.isBlank( latitude ) ){
            latitude = "0.0" ;
        }
        otherParams.add("eventLocationCoordinate=" +
                latitude + "," +  longitude );//???????????????????????????lat,lng Required
        otherParams.add("eventAlarmManPhone=" + incidentBean.getAlarmPersonPhone()  );//???????????????
        otherParams.add("enterpriseId=" + ""  );//????????????ID
        otherParams.add("dataSource=" + incidentBean.getId()  );//????????????ID  Required

        String  methodUrl = "/fight/modify" ;

        //1?????????????????????????????????????????????accessKey???timestamp???nonce???
        List<String> params = new ArrayList<String>();
        params.add("page=1");//??????????????????
        params.add("accessKey="+accessKey);
        params.add("timestamp="+ servletService.getSystemTime() );
        params.add("nonce="+ Math.random());//GUID.random()??????????????????????????????????????????

        if( otherParams != null && otherParams.size() > 0 ){
            params.addAll( otherParams ) ;
        }

        params.sort(String::compareTo);

        //2??????&??????????????????????????????
        String paramsStr = params.stream().collect(Collectors.joining("&"));

        //3??????????????????????????????????????? secretKey
        String unsigned = paramsStr + "&secretKey=" + secretKey;

        //4?????????md5???
        String sign= DigestUtils.md5Hex( unsigned  ) ;

        //5??????????????????md5?????????????????????accessKey???timestamp???nonce????????????
        String data= paramsStr + "&sign="+sign;

        //6???????????????
        String url = host + methodUrl;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpPost post = new HttpPost(url);
                logService.infoLog(logger, "service", "sendHttp url ", url  );
                logService.infoLog(logger, "service", "sendHttp params  data ", data  );
                logService.writeLog(logger, "service", "sendHttp params  path ",url + "?" + data  );
                try {
                    StringEntity entity = new StringEntity(data, Charset.forName("UTF-8"));
                    entity.setContentEncoding("utf-8");
                    entity.setContentType("application/x-www-form-urlencoded");
                    post.setEntity(entity);
                    HttpClient client = wrapClient( url ) ;
                    HttpResponse response = client.execute(post);
                    HttpEntity responseEntity = response.getEntity();
                    String  result = EntityUtils.toString(responseEntity, "utf-8");
                    logService.writeLog(logger, "service", "sendHttp result ", result  );

                    //??????????????????

                } catch (Exception ex) {
                    logService.erorLog(logger, "service", "sendHttp", String.format(" send zhi jian data fail   " ) ,ex  );
                }finally {
                    if (post != null){
                        post.releaseConnection();
                    }

                }

            }
        });
        thread.start();

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "modifyCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;

    }




    @Transactional
    @Override
    public Boolean dispatchVehicle(IncidentBean incidentBean, List<HandleBean> handleBeans) {
        logService.infoLog(logger, "service", "modifyCase", "service is started...");
        Long logStart = System.currentTimeMillis();
        if (  !whetherZhiJian ){
            return false;
        }

        //????????????id?????? ????????????????????????id
        IncidentExtend119Entity incidentExtend119Entity = accessor.getById( incidentBean.getId() , IncidentExtend119Entity.class ) ;
        if( incidentExtend119Entity != null ){

            List<String> otherParams = new ArrayList<String>();
            otherParams.add("id=" + incidentExtend119Entity.getOtherIncidentIdOne()  );//????????????ID  Required  ??????????????????id
    //        othersParams.add("dispatchOrgIds=" + ""  );//????????????ID???????????????
    //        othersParams.add("dispatchOrgNames=" + ""  );//????????????????????????????????????????????????????????????????????????
    //        othersParams.add("dispatchPlateNumbers=" + ""  );//??????????????????????????????
            //?????????????????????
            String handleVehicle = "" ;
            for( HandleBean handleBean :  handleBeans ){
                handleVehicle = handleBean.getDispatchVehicle() ;
            }
            //????????????ids ???????????????
            List<String> handleVehicleIds =  Arrays.asList( handleVehicle.split(",") );
            List<String> frameNoStr  =  vehicleService.findframeNoCacheByIds( handleVehicleIds ) ;
            String dispatchCarFrameNumbers = StringUtils.join(frameNoStr.toArray(), ",") ;
            otherParams.add("dispatchCarFrameNumbers=" + dispatchCarFrameNumbers  );//??????????????????????????????

            String  methodUrl = "/fight/dispatch" ;

            //1?????????????????????????????????????????????accessKey???timestamp???nonce???
            List<String> params = new ArrayList<String>();
            params.add("page=1");//??????????????????
            params.add("accessKey="+accessKey);
            params.add("timestamp="+ servletService.getSystemTime() );
            params.add("nonce="+ Math.random());//GUID.random()??????????????????????????????????????????

            if( otherParams != null && otherParams.size() > 0 ){
                params.addAll( otherParams ) ;
            }

            params.sort(String::compareTo);

            //2??????&??????????????????????????????
            String paramsStr = params.stream().collect(Collectors.joining("&"));

            //3??????????????????????????????????????? secretKey
            String unsigned = paramsStr + "&secretKey=" + secretKey;

            //4?????????md5???
            String sign= DigestUtils.md5Hex( unsigned  ) ;

            //5??????????????????md5?????????????????????accessKey???timestamp???nonce????????????
            String data= paramsStr + "&sign="+sign;

            //6???????????????
            String url = host + methodUrl;

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpPost post = new HttpPost(url);
                    logService.infoLog(logger, "service", "sendHttp url ", url  );
                    logService.infoLog(logger, "service", "sendHttp params  data ", data  );
                    logService.writeLog(logger, "service", "sendHttp params  path ",url + "?" + data  );
                    try {
                        StringEntity entity = new StringEntity(data, Charset.forName("UTF-8"));
                        entity.setContentEncoding("utf-8");
                        entity.setContentType("application/x-www-form-urlencoded");
                        post.setEntity(entity);
                        HttpClient client = wrapClient( url ) ;
                        HttpResponse response = client.execute(post);
                        HttpEntity responseEntity = response.getEntity();
                        String  result = EntityUtils.toString(responseEntity, "utf-8");
                        logService.writeLog(logger, "service", "sendHttp result ", result  );

                        //??????????????????

                    } catch (Exception ex) {
                        logService.erorLog(logger, "service", "sendHttp", String.format(" send zhi jian data fail   " ) ,ex  );
                    }finally {
                        if (post != null){
                            post.releaseConnection();
                        }

                    }

                }
            });
            thread.start();
        }
        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "modifyCase", String.format("service is finished,execute time is :%sms", logEnd - logStart));
        return true;
    }



    /**
     * ??????url????????????http or https???client
     * @param url
     * @return
     */
    private HttpClient wrapClient(String url){
        if (url != null && url.startsWith("https://")){
            return sslClient();
        }else {
            HttpClient httpClient = HttpClientBuilder.create().build();
            return httpClient;
        }
    }

    /**
     * ?????????SSL?????????????????????????????????????????????SSL
     * ??????ConnectionManager?????????Connection????????????
     *
     * @return HttpClient ??????HTTPS
     */
    private  HttpClient sslClient() {
        try {
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[]{trustManager}, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            //??????Register
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE)
                    .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                    .build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", socketFactory)
                    .build();
            //??????ConnectionManager?????????Connection????????????
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig)
                    .build();
            return closeableHttpClient;
        } catch (NoSuchAlgorithmException e) {
            logService.erorLog(logger, "service", "sendHttp", "====== NoSuchAlgorithmException happened : {} ======", e );
        } catch (KeyManagementException e) {
            logService.erorLog(logger, "service", "sendHttp", "====== KeyManagementException happened : {} ======", e );
        }
        return null;
    }







}
