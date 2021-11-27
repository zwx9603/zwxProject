package com.dscomm.iecs.schedule.tasks.hangzhou;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.schedule.service.hangzhou.BuildTokenService;
import org.apache.logging.log4j.util.Strings;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 描述： 杭州  获得大华视频信息
 */
@Component("videoDhTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class VideoDhTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(VideoDhTask.class);

    private LogService logService;
    private Environment env ;

    private BuildTokenService buildTokenService ;



    @Autowired
    public VideoDhTask(LogService logService  , Environment env , BuildTokenService buildTokenService ) {
        this.logService = logService;
        this.buildTokenService = buildTokenService ;
        this.env = env ;
    }

    @Override
    public void invoke() {


        // 推送大华用户信息 其他视频
        try {
            logService.writeLog(logger, "tasks", "videoDhTask", "start excute tasks");
            long start = System.currentTimeMillis();

            String token = null ;
            String tokenUrl = env.getProperty("tokenUrl","http://41.253.39.179:8314") ;

            if(Strings.isBlank( token ) ){
                String userName = env.getProperty("tokenUserName","system") ;
                String password = env.getProperty("tokenPassword","system123456" ) ;

                 token = buildTokenService.authorize( tokenUrl , userName , password ) ;
            }
            if( Strings.isNotBlank( token ) ){

                buildTokenService.saveDahuaVideoQTSP( tokenUrl , token  );

                Boolean unauthorize = buildTokenService.unauthorize( tokenUrl , token ) ;
                token = null;
            }

            long end = System.currentTimeMillis();
            logService.writeLog(logger, "tasks", "videoDhTask", String.format("end excute tasks,totalTime:%sms", end - start));
        } catch (Exception ex) {
            logService.erorLog(logger, "tasks", "videoDhTask", "videoDhTask  fail", ex);
        }
    }








}
