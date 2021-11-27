package com.dscomm.iecs.notify.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mx.comps.notify.config.NotificationServerConfig;
import org.mx.service.server.config.ServerConfig;
import org.mx.spring.config.SpringCacheConfig;
import org.mx.spring.config.SpringConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * 描述:notify模块配置文件
 *
 */
@Configuration
@EnableApolloConfig({"server"})
@PropertySource({
        "classpath:server.properties"
})
@Import({
        SpringConfig.class,
        SpringCacheConfig.class,
        ServerConfig.class,
        NotificationServerConfig.class
})

@ComponentScan({
        "com.dscomm.iecs.**.service"
})
public class IecsNotifyConfig {



}
