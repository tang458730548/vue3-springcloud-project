package com.lihusoft;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 描述：
 *      SpringCloud-Eureka注册中心
 * @author tsc
 * @since 2023/2/7 14:22
 **/
@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class ApplicationEureka {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEureka.class , args);
        log.info("启动SpringCloud-Eureka成功.............");
    }
}
