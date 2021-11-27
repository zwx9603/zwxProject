package com.dscomm.iecs.schedule.tasks.hangzhou;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.utils.HttpClientUtil;
import com.dscomm.iecs.schedule.service.hangzhou.BuildTokenService;
import com.dscomm.iecs.schedule.service.hangzhou.PushDhService;
import org.apache.logging.log4j.util.Strings;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述： 杭州 推送大华用户信息
 */
@Component("pushDhTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class PushDhTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(PushDhTask.class);
    private LogService logService;

    private PushDhService pushDhService ;
    private Environment env ;
    private BuildTokenService buildTokenService ;

    @Autowired
    public PushDhTask(LogService logService , PushDhService pushDhService   ,Environment env , BuildTokenService buildTokenService ) {
        this.logService = logService;
        this.pushDhService = pushDhService ;
        this.env = env ;
        this.buildTokenService = buildTokenService ;
    }

    @Override
    public void invoke() {
        // 推送大华用户信息
        try {
            logService.writeLog(logger, "tasks", "PushDhTas" +
                    "k", "start excute tasks");
            long start = System.currentTimeMillis();

            String tokenUrl = env.getProperty("tokenUrl","http://41.253.39.179:8314") ;
            String pushDhUrl = env.getProperty("pushDhUrl","http://41.253.39.179:6642") ;
            String token = null ;

            if(Strings.isBlank( token ) ){

                String userName = env.getProperty("pushDhUserName","test008") ;
                String password = env.getProperty("pushDhPassword","admin@123456" ) ;

                token = buildTokenService.authorize( tokenUrl , userName , password ) ;
            }
            if( Strings.isNotBlank( token ) ){
                List<JSONObject> execute = pushDhService.PushDh() ;

                System.out.println( token );

                if( execute != null && execute.size() > 0 ){
                    for (JSONObject s : execute) {
                        try {
                            Boolean bl = buildTokenService.keepalive( tokenUrl , token ) ;
                            System.out.println( bl );

                            Map header = new HashMap<>();
                            header.put("X-Subject-Token", token  );
                            header.put("Content-Type", "application/json;charset=UTF-8" );
                            HttpClientUtil.doPost(pushDhUrl + "/brmu/firePerson", s.toJSONString(), header);

                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                Boolean unauthorize = buildTokenService.unauthorize( tokenUrl , token ) ;

                token = null;
            }


            long end = System.currentTimeMillis();
            logService.writeLog(logger, "tasks", "PushDhTask", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "PushDhTask", "force update cache dictionary fail", ex);
        }
    }
}
