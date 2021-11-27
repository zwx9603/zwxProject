package com.dscomm.iecs.hangzhou.config;

import org.mx.service.server.config.ServerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * 描述:notify模块配置文件
 *
 */
@Configuration
@PropertySource({
        "classpath:server.properties"
})
@Import({
        ServerConfig.class,
})

@ComponentScan({
        "com.dscomm.iecs.**.service"
})
public class IecsHangzhouConfig {



}
