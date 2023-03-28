package com.lihusoft.error;

import com.lihusoft.constants.HttpStatusEnum;
import com.lihusoft.http.HttpResultUtil;
import com.lihusoft.http.HttpResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/2/10 11:35
 **/
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public HttpResultVo<String> errorHandler(AuthorizationException e) {
        log.error("权限校验失败！", e);
        return HttpResultUtil.build(
                HttpStatusEnum.HTTP_403.getCode() ,
                HttpStatusEnum.HTTP_403.getMessage());
    }
}
