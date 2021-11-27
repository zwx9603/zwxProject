package com.zwx;

import com.sun.glass.ui.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:config/data.properties",
        "classpath:config/my.properties",
        "classpath:config/test.properties",
        "classpath:application-dev.yml"
})
@MapperScan("com.zwx.basedata.mapper")
public class DemoWebApplication{
    public static void main(String[] args) {
        SpringApplication.run(DemoWebApplication.class,args);
    }
}
