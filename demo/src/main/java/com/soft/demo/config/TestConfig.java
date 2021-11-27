package com.soft.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:config/data.properties",
        "classpath:config/my.properties",
        "classpath:config/test.properties",
        "classpath:application-dev.yml"
})
public class TestConfig {

}
