package com.lihusoft.controller;

import com.lihusoft.http.HttpResultUtil;
import com.lihusoft.http.HttpResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/3/28 11:59
 **/
@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("test")
    public HttpResultVo<String> test(){
        LOGGER.info("打印日志");
        return HttpResultUtil.build("hello world!");
    }
}
