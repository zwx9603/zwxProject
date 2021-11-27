package com.dscomm.iecs.schedule.service.hangzhou;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.RestfulClient;
import com.dscomm.iecs.keydata.service.ServletService;
import com.dscomm.iecs.schedule.exception.ScheduleException;
import com.dscomm.iecs.schedule.factory.ConnectionsFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("buildTokenServiceImpl")
public class  BuildTokenServiceImpl implements BuildTokenService {

    private static final Logger logger = LoggerFactory.getLogger(BuildTokenServiceImpl.class);
    private LogService logService;
    private Environment env ;
    private ConnectionsFactory connectionsFactory;
    private ServletService servletService ;

    @Autowired
    public BuildTokenServiceImpl( LogService logService , Environment env , ConnectionsFactory connectionsFactory ,
                                  ServletService servletService
    ){
        this.logService = logService ;
        this.env = env ;
        this.connectionsFactory = connectionsFactory ;
        this.servletService = servletService ;

    }

    /**
     * 注册会话
     * @return
     */
    @Override
    public String  authorize( String tokenUrl , String userName , String  password ) {
        String  token = null ;
        try {

            //第一次交互
            String url = tokenUrl + "/videoService/accounts/authorize" ;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName", userName );
            jsonObject.put("clientType", "web");
            String body = jsonObject.toJSONString() ;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8" );
            HttpEntity<?> entity = new HttpEntity<>(body , headers);

            ResponseEntity<String> result = RestfulClient.getClient().exchange(url, HttpMethod.POST, entity, String.class);

            Integer resultStatus = result.getStatusCodeValue()  ;

            //第一次交互 状态码 返回 401 正常
            if ( 401 == resultStatus ) {
                String resultBodyOne = result.getBody() ;
                Map<String , String> resultMapOne = ( Map<String, String> ) JSONObject.parse( resultBodyOne ) ;

                //进行第二次交互 参数
                String dazh = userName   ;
                String daps =  password ;
                String realm = resultMapOne.get( "realm") ;
                String randomKey = resultMapOne.get( "randomKey") ;
                String encryptType = resultMapOne.get( "encryptType" ) ;

                if( "MD5".equals( encryptType )  ){
                    String passwdvalue = DigestUtils.md5Hex( daps ) ;

                    String passwdvalue1 = DigestUtils.md5Hex(dazh +  passwdvalue ) ;
                    String passwdtmp = DigestUtils.md5Hex(passwdvalue1 ) ;
                    String encryptedpasswd = DigestUtils.md5Hex(dazh + ":" + realm + ":" + passwdtmp ) ;
                    String signature = DigestUtils.md5Hex(encryptedpasswd + ":" + randomKey ) ;

                    //开始进行第二次交互

                    JSONObject jsonObjectTwo = new JSONObject();
                    jsonObjectTwo.put("userName", userName );
                    jsonObjectTwo.put("clientType", "web");
                    jsonObjectTwo.put("randomKey", randomKey );
                    jsonObjectTwo.put("signature", signature );
                    jsonObjectTwo.put("encryptType", encryptType );
                    String bodyTwo = jsonObjectTwo.toJSONString() ;

                    HttpHeaders headersTwo = new HttpHeaders();
                    headersTwo.add("Content-Type", "application/json;charset=UTF-8" );
                    HttpEntity<?> entityTwo = new HttpEntity<>(bodyTwo , headersTwo);

                    ResponseEntity<String> resultTwo = RestfulClient.getClient().exchange(url, HttpMethod.POST, entityTwo , String.class);

                    Integer resultStatusTwo = resultTwo.getStatusCodeValue()  ;

                    if( 200 == resultStatusTwo ){
                        String resultBodyTwo = resultTwo.getBody() ;
                        Map<String , String> resultMapTwo = ( Map<String, String> ) JSONObject.parse( resultBodyTwo ) ;
                        token = resultMapTwo.get( "token" ) ;
                    }
                }
            }

        }catch ( Exception ex ){
            logService.erorLog(logger, "service", "authorize", "authorize  fail", ex);
        }
        return  token ;
    }



    /**
     * 保活会话
     * @return
     */
    @Override
    public   Boolean keepalive( String tokenUrl , String token) {
        Boolean  keepalive = false  ;
        try {


            String url = tokenUrl + "/videoService/accounts/token/keepalive" ;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token );
            jsonObject.put("duration", 120 );
            String body = jsonObject.toJSONString() ;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8" );
            headers.add("X-Subject-Token", token );
            HttpEntity<?> entity = new HttpEntity<>( body , headers);

            ResponseEntity<String> result = RestfulClient.getClient().exchange(url, HttpMethod.PUT , entity, String.class);

            Integer resultStatus = result.getStatusCodeValue()  ;

            if( 200 == resultStatus ){
                keepalive = true ;
            }
        }catch ( Exception ex ){
            logService.erorLog(logger, "service", "keepalive", "authorize  fail", ex);
        }
        return  keepalive ;
    }

    /**
     * 销毁会话
     * @return
     */
    @Override
    public  Boolean unauthorize(String tokenUrl , String token)  {
        Boolean  unauthorize = false ;
        try {
            //第一次交互
            String url = tokenUrl + "/videoService/accounts/unauthorize" ;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token );
            String body = jsonObject.toJSONString() ;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8" );
            headers.add("X-Subject-Token", token );
            HttpEntity<?> entity = new HttpEntity<>( body , headers);

            ResponseEntity<String> result = RestfulClient.getClient().exchange(url, HttpMethod.POST , entity, String.class);

            Integer resultStatus = result.getStatusCodeValue()  ;

            if( 200 == resultStatus ){
                unauthorize = true ;
            }
        }catch ( Exception ex ){
            logService.erorLog(logger, "service", "keepalive", "authorize  fail", ex);
        }
        return  unauthorize ;
    }

//
//    @Override
//    public   void saveTest(String tokenUrl , String  token )   {
//
//        String url = tokenUrl + "/videoService/devicesManager/deviceTree" ;
////        String url = tokenUrl + "/videoService/devicesManager/devicesInfo" ;
//
//
//        int nodeType = 1 ;
////        String typeCode = "01;1;1;1;" ;
//        String typeCode = "01;0;ALL;ALL";
//        int page = 1 ;
//        int pageSize = 100 ;
//        String  id = env.getProperty("4gdbspOrgId","S4NbecfYB1CGSTVQ9EH6N4") ; // 2 4G单兵 3 营区监控视频  在同一个节点下
//        test1(tokenUrl ,  token , url , id   , nodeType , typeCode , page , pageSize  ,null, null     ) ;
//    }
//
//
//    private void test1( String tokenUrl , String  token , String baseUrl , String id ,int nodeType , String typeCode , int page , int pageSize ,
//                                 String type , List<String> qtIds    ){
//        try {
//            //保活
//            Boolean keepalive =  keepalive( tokenUrl , token ) ;
//
//            String url = baseUrl
//                    + "?id=" + id + "&nodeType=" + nodeType + "&typeCode=" + typeCode + "&page=" + page + "&pageSize=" + pageSize
//                    ;
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Type", "application/json;charset=UTF-8" );
//            headers.add("X-Subject-Token", token );
//            HttpEntity<?> entity = new HttpEntity<>(  headers);
//
////            JSONObject body = new JSONObject() ;
////            body.put( "orgCode" , id ) ;
////            HttpEntity<?> entity = new HttpEntity<>( body , headers);
//
//            ResponseEntity<String> result = RestfulClient.getClient().exchange(url, HttpMethod.GET , entity, String.class);
////            ResponseEntity<String> result = RestfulClient.getClient().exchange(url, HttpMethod.POST , entity, String.class);
//
//
//            Integer resultStatus = result.getStatusCodeValue()  ;
//
//            if( 200 == resultStatus ){
//                String resultBodyOne = result.getBody() ;
//                Map<String , Object> resultMapOne = ( Map<String, Object> ) JSONObject.parse( resultBodyOne ) ;
////                int currentPage =   ( int )resultMapOne.get( "currentPage")  ;
////                int nextPage =  ( int ) resultMapOne.get( "nextPage")   ;
//                JSONArray results = (JSONArray)resultMapOne.get("results") ;
//                if( results != null ){
//                    List<Map> resultsMap = JSONObject.parseArray( results.toString() , Map.class );
//                    if( resultsMap != null && resultsMap.size() > 0 ){
//                        for( Map resultMap : resultsMap ){
//                            String resultId = ( String ) resultMap.get("id") ;
//                            String channelId = ( String ) resultMap.get("channelId") ;
//                            int resultPage = 1 ;
//                            //判断 channelId 是否存在  如果存在为设备 不存在继续找
//                            if(Strings.isBlank( channelId )){
//                                findAndSaveGao( tokenUrl , token , baseUrl , resultId  , nodeType , typeCode , resultPage , pageSize ,type  , qtIds  );
//                            }else{
//                                //保存修改 视频设备信息
//                                logService.infoLog(logger, "service","saveOrUpdateVideo","save gao video ,id is : " + resultMap.get("id"));
//                                //判断是否为单兵视频
//                                String name =  ( String ) resultMap.get( "name" ) ;
//                                String tempType = type ;
//                                if( "3".equals( type ) &&  name.contains("单兵") ){
//                                    tempType  = "2" ; // 4G单兵
//                                }
//                                saveOrUpdateVideo( resultMap ,tempType  );
//                            }
//                        }
//                    }
//                }
////                //判断是否存在 下一页
////                if( -1 != nextPage ){
////                    findAndSaveGao( tokenUrl ,  token , baseUrl , id  , nodeType ,typeCode , nextPage , pageSize ,type  , qtIds   );
////                }
//            }
//        }catch ( Exception ex ){
//            logService.erorLog(logger, "service", "saveDahuaVideo", "authorize  fail", ex);
//        }
//    }



    @Override
    public   void saveDahuaVideoGaoWRJSP(String tokenUrl ,   String  token ) {
        String url = tokenUrl + "/videoService/devicesManager/deviceTree" ;
        int nodeType = 1 ;
        String typeCode = "01;1;ALL;ALL";
        int page = 1 ;
        int pageSize = 100 ;
        String  id = env.getProperty("wrjspOrgId","320101") ;
        String type = "1" ; //区分  1无人机 2 4G单兵 3 营区监控视频 4机器人视频   9其他视频
        findAndSaveGao( tokenUrl , token , url , id   , nodeType , typeCode , page , pageSize  ,type  , null   ) ;
    }

    @Override
    public   void saveDahuaVideoGao4GSP( String tokenUrl , String  token )   {
        String url = tokenUrl + "/videoService/devicesManager/deviceTree" ;
        int nodeType = 1 ;
        String typeCode = "01;1;ALL;ALL";
        int page = 1 ;
        int pageSize = 100 ;
        String  id = env.getProperty("4gdbspOrgId","00001_3301") ; // 2 4G单兵 3 营区监控视频  在同一个节点下
        String type = "3" ; //区分  1无人机 2 4G单兵 3 营区监控视频 4机器人视频   9其他视频
        findAndSaveGao(tokenUrl ,  token , url , id   , nodeType , typeCode , page , pageSize  ,type, null     ) ;
    }


    @Override
    public    void saveDahuaVideoGaoJQRSP( String tokenUrl , String  token )  {
        String url = tokenUrl + "/videoService/devicesManager/deviceTree" ;
        int nodeType = 1 ;
        String typeCode = "01;1;ALL;ALL";
        int page = 1 ;
        int pageSize = 100 ;
        String  id = env.getProperty("jqrspOrgId","S4NbecfYB1CGSTVQ9EH6N4") ;
        String type = "4" ; //区分  1无人机 2 4G单位 3 营区监控视频 4机器人视频   9其他视频
        findAndSaveGao( tokenUrl ,  token , url , id   , nodeType , typeCode , page , pageSize  ,type , null    ) ;
    }




    @Override
    public   void saveDahuaVideoQTSP( String tokenUrl , String  token )   {
        String url = tokenUrl + "/videoService/devicesManager/deviceTree" ;
        int nodeType = 1 ;
        String typeCode = "01;1;ALL;ALL";
        int page = 1 ;
        int pageSize = 100 ;
        String  id =  null  ;
        String type = "9" ; //区分  1无人机 2 4G单兵 3 监控视频 4机器人视频   9其他视频
        List<String> qtIds = new ArrayList<>() ;
        qtIds.add(  env.getProperty("wrjspOrgId","320101")  ) ;
        qtIds.add(  env.getProperty("4gdbspOrgId","00001_3301") ) ;
        qtIds.add(  env.getProperty("jqrspOrgId","S4NbecfYB1CGSTVQ9EH6N4") ) ;

        findAndSave( tokenUrl ,  token , url , id , nodeType , typeCode , page , pageSize , type  ,qtIds     ) ;
    }


    private void findAndSave( String tokenUrl ,  String  token , String baseUrl , String id ,int nodeType , String typeCode , int page , int pageSize ,
                              String type  , List<String> qtIds  ){
        try {
            //保活
            Boolean keepalive =  keepalive( tokenUrl,  token ) ;

            removeVideoByOrgCode( id ) ;

            if( Strings.isNotBlank( id ) && qtIds != null  && qtIds.contains( id ) ){
                return ;
            }


            String url = baseUrl + "?id=" + id + "&nodeType=" + nodeType + "&typeCode=" + typeCode + "&page=" + page + "&pageSize=" + pageSize ;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8" );
            headers.add("X-Subject-Token", token );
            HttpEntity<?> entity = new HttpEntity<>(  headers);

            ResponseEntity<String> result = RestfulClient.getClient().exchange(url, HttpMethod.GET , entity, String.class);


            Integer resultStatus = result.getStatusCodeValue()  ;

            if( 200 == resultStatus ){
                String resultBodyOne = result.getBody() ;
                Map<String , Object> resultMapOne = ( Map<String, Object> ) JSONObject.parse( resultBodyOne ) ;
                int currentPage =   ( int )resultMapOne.get( "currentPage")  ;
                int nextPage =  ( int ) resultMapOne.get( "nextPage")   ;
                JSONArray results = (JSONArray)resultMapOne.get("results") ;
                if( results != null ){
                    List<Map> resultsMap = JSONObject.parseArray( results.toString() , Map.class );
                    if( resultsMap != null && resultsMap.size() > 0 ){
                        for( Map resultMap : resultsMap ){
                            String resultId = ( String ) resultMap.get("id") ;
                            String channelId = ( String ) resultMap.get("channelId") ;
                            int resultPage = 1 ;
                            //判断 channelId 是否存在  如果存在为设备 不存在继续找
                            if(Strings.isBlank( channelId )){
                                findAndSave(tokenUrl ,  token , baseUrl , resultId  , nodeType , typeCode , resultPage , pageSize , type  , qtIds  );
                            }else{
                                //保存修改 视频设备信息
                                logService.infoLog(logger, "service","saveOrUpdateVideo","save video ,id is :" + resultMap.get("id"));
                                saveOrUpdateVideo( resultMap , type );
                            }
                        }
                    }
                }
                //判断是否存在 下一页
                if( -1 != nextPage ){
                    findAndSave( tokenUrl ,  token , baseUrl , id  , nodeType ,typeCode , nextPage , pageSize , type  , qtIds  );
                }
            }
        }catch ( Exception ex ){
            logService.erorLog(logger, "service", "saveDahuaVideo", "authorize  fail", ex);
        }
    }


    private void findAndSaveGao( String tokenUrl , String  token , String baseUrl , String id ,int nodeType , String typeCode , int page , int pageSize ,
                                 String type , List<String> qtIds    ){
        try {
            //保活
            Boolean keepalive =  keepalive( tokenUrl , token ) ;

            String url = baseUrl + "?id=" + id + "&nodeType=" + nodeType + "&typeCode=" + typeCode + "&page=" + page + "&pageSize=" + pageSize ;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8" );
            headers.add("X-Subject-Token", token );
            HttpEntity<?> entity = new HttpEntity<>(  headers);

            ResponseEntity<String> result = RestfulClient.getClient().exchange(url, HttpMethod.GET , entity, String.class);


            Integer resultStatus = result.getStatusCodeValue()  ;

            if( 200 == resultStatus ){
                String resultBodyOne = result.getBody() ;
                Map<String , Object> resultMapOne = ( Map<String, Object> ) JSONObject.parse( resultBodyOne ) ;
                int currentPage =   ( int )resultMapOne.get( "currentPage")  ;
                int nextPage =  ( int ) resultMapOne.get( "nextPage")   ;
                JSONArray results = (JSONArray)resultMapOne.get("results") ;
                if( results != null ){
                    List<Map> resultsMap = JSONObject.parseArray( results.toString() , Map.class );
                    if( resultsMap != null && resultsMap.size() > 0 ){
                        for( Map resultMap : resultsMap ){
                            String resultId = ( String ) resultMap.get("id") ;
                            String channelId = ( String ) resultMap.get("channelId") ;
                            int resultPage = 1 ;
                            //判断 channelId 是否存在  如果存在为设备 不存在继续找
                            if(Strings.isBlank( channelId )){
                                findAndSaveGao( tokenUrl , token , baseUrl , resultId  , nodeType , typeCode , resultPage , pageSize ,type  , qtIds  );
                            }else{
                                //保存修改 视频设备信息
                                logService.infoLog(logger, "service","saveOrUpdateVideo","save gao video ,id is : " + resultMap.get("id"));
                                //判断是否为单兵视频
                                String name =  ( String ) resultMap.get( "name" ) ;
                                String tempType = type ;
                                if( "3".equals( type ) &&  name.contains("单兵") ){
                                    tempType  = "2" ; // 4G单兵
                                }
                                saveOrUpdateVideo( resultMap ,tempType  );
                            }
                        }
                    }
                }
                //判断是否存在 下一页
                if( -1 != nextPage ){
                    findAndSaveGao( tokenUrl ,  token , baseUrl , id  , nodeType ,typeCode , nextPage , pageSize ,type  , qtIds   );
                }
            }
        }catch ( Exception ex ){
            logService.erorLog(logger, "service", "saveDahuaVideo", "authorize  fail", ex);
        }
    }



    private static String sql = "INSERT INTO t_tyqx_dtss_sp( " +
            " gid,lx,fl,cjsj,gxsj," +
            " spmc,spbm,spdw,lat,lon," +
            " zt,yxx,ssid,cxmlj,datasources" +
            " ) VALUES ( " +
            " ? ,? , ? , ? , ? , " +
            " ? ,? , ? , ? , ? ,  " +
            " ?, ? , ? , ? , ? " +
            " ) ON conflict ( gid ) DO " +
            " UPDATE  SET " +
            " lx =  ?," +
            " fl = ?," +
            " gxsj = ?," +
            " spmc = ?," +
            " spbm = ?, " +
            " spdw =  ?," +
            " lat =?," +
            " lon =?," +
            " zt =?, " +
            " yxx =?," +
            " ssid =?," +
            " cxmlj =?," +
            " datasources =?   "   ;

    /**
     * 从原数据库查询删除数据
     *
     * @return 返回查询结果
     */
    private void saveOrUpdateVideo(  Map resultMap , String type  ) {
        PreparedStatement pstat = null;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("video") ) ) {
            pstat = conn.prepareStatement( sql );
            Long currentTime = servletService.getSystemTime() ;
            pstat.setString( 1,( String )resultMap.get( "id")  ); //主键
            pstat.setString( 2, type ); //类型
            pstat.setString( 16, type ); //类型
            pstat.setString( 3,( String )resultMap.get( "category") );//分类
            pstat.setString( 17,( String )resultMap.get( "category") );//分类
            pstat.setTimestamp( 4, new Timestamp( currentTime + ( 8  * 3600 * 1000 )  )  );//创建时间 Timestamp gmt
            pstat.setTimestamp( 5, new Timestamp( currentTime + ( 8 * 3600 * 1000 ) ) );//更新时间 Timestamp gmt
            pstat.setTimestamp( 18, new Timestamp( currentTime + ( 8 * 3600 * 1000 ) )  );//更新时间 Timestamp gmt
            pstat.setString( 6,( String )resultMap.get( "name"));//视频名称
            pstat.setString( 19,( String )resultMap.get( "name"));//视频名称
            pstat.setString( 7,( String )resultMap.get( "deviceCode"));//视频编码
            pstat.setString( 20,( String )resultMap.get( "deviceCode"));//视频编码
            pstat.setString( 8,( String )resultMap.get( "orgCode") );//视频所属单位
            pstat.setString( 21,( String )resultMap.get( "orgCode") );//视频所属单位
            pstat.setFloat( 9, Strings.isBlank(  (String ) resultMap.get( "gpsY") ) ? 0.0f : Float.parseFloat(  (String ) resultMap.get( "gpsY") )  );//纬度
            pstat.setFloat( 22,Strings.isBlank(  (String ) resultMap.get( "gpsY") ) ? 0.0f : Float.parseFloat(  (String ) resultMap.get( "gpsY") ) );//纬度
            pstat.setFloat( 10,Strings.isBlank(  (String ) resultMap.get( "gpsX") ) ? 0.0f : Float.parseFloat(  (String ) resultMap.get( "gpsX") ) );//经度
            pstat.setFloat( 23,Strings.isBlank(  (String ) resultMap.get( "gpsX") ) ? 0.0f : Float.parseFloat(  (String ) resultMap.get( "gpsX") ) );//经度
            pstat.setString( 11,  (String) resultMap.get( "online")    );//视频状态
            pstat.setString( 24,    (String) resultMap.get( "online")     );//视频状态
            pstat.setInt( 12,1);//有效性
            pstat.setInt( 25,1);//有效性
            pstat.setString( 13,( String )resultMap.get( "channelId") );//设施id
            pstat.setString( 26,( String )resultMap.get( "channelId") );//设施id
            pstat.setString( 14,( String )resultMap.get( "orgCode") );//查询码路径
            pstat.setString( 27,( String )resultMap.get( "orgCode") );//查询码路径
            pstat.setString( 15,"dahua" + currentTime );//数据来源
            pstat.setString( 28,"dahua" + currentTime );//数据来源
            Boolean execute  = pstat.execute();

        } catch ( Exception ex) {
            ex.printStackTrace(); ;
            logService.erorLog(logger, "service", "saveOrUpdateVideo", String.format(" save schedule table delete fail  , target time :%s.", new Date( System.currentTimeMillis() ) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SAVE_DATA_FAIL);
        }finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
            }catch ( Exception ex ){
                logService.erorLog(logger, "service", "saveOrUpdateVideo", String.format(" find schedule table delete fail  , target time :%s.", new Date( System.currentTimeMillis() ) ), ex);
                throw new ScheduleException(ScheduleException.ScheduleErrors.SAVE_DATA_FAIL);
            }
        }

    }


    private static String removeSql = " update  t_tyqx_dtss_sp set yxx = 0 ,gxsj = ?  where yxx = 1 and spdw = ?  and gxsj <= ?  "   ;


    private void removeVideoByOrgCode(   String orgCode  ) {
        PreparedStatement pstat = null;
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("video") ) ) {
            pstat = conn.prepareStatement( removeSql );
            Long currentTime = servletService.getSystemTime() ;
            pstat.setTimestamp( 1, new Timestamp( currentTime + ( 8  * 3600 * 1000 )  )  );//更新时间 Timestamp gmt
            pstat.setString( 2,orgCode ); //单位id
            pstat.setTimestamp( 3, new Timestamp( currentTime - ( 14 * 24  * 3600 * 1000 )  )  );//更新时间 Timestamp gmt
            Boolean execute  = pstat.execute();

        } catch ( Exception ex) {
            ex.printStackTrace(); ;
            logService.erorLog(logger, "service", "removeVideoByOrgCode", String.format(" save schedule table delete fail  , target time :%s.", new Date( System.currentTimeMillis() ) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SAVE_DATA_FAIL);
        }finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
            }catch ( Exception ex ){
                logService.erorLog(logger, "service", "removeVideoByOrgCode", String.format(" find schedule table delete fail  , target time :%s.", new Date( System.currentTimeMillis() ) ), ex);
                throw new ScheduleException(ScheduleException.ScheduleErrors.SAVE_DATA_FAIL);
            }
        }

    }
}
