package com.dscomm.iecs.keydata.config;

import org.mx.dal.config.DalHibernateConfig;
import org.mx.service.server.config.ServerConfig;
import org.mx.spring.config.SpringConfig;
import org.mx.spring.config.SpringI18nConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 描述:敏感信息配置类
 *
 * @author YangFuxi
 * Date Time 2019/8/16 14:29
 */
@Configuration
@PropertySource({
        "classpath:server.properties",
        "classpath:graphql.properties",
        "classpath:jpa.properties",
        "classpath:fixed.properties",
        "classpath:changeability.properties"

})
@Import({
        SpringI18nConfig.class,
        SpringConfig.class,
        DalHibernateConfig.class,
        ServerConfig.class
})
@EnableJpaRepositories({
        "com.dscomm.iecs.keydata.dal.repository"
})
@ComponentScan({
        "com.dscomm.iecs.keydata.graphql",
        "com.dscomm.iecs.keydata.service"
})

public class IecsKeydataConfig {
}
