package com.lihusoft;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/3/28 11:20
 **/

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class ApplicationServiceBase {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationServiceBase.class, args);
        log.info("启动ApplicationServiceBase成功");
    }
}
