package com.lihusoft.controller;

import com.alibaba.fastjson.JSONObject;
import com.lihusoft.constants.HttpStatusEnum;
import com.lihusoft.http.HttpResultUtil;
import com.lihusoft.http.HttpResultVo;
import com.lihusoft.request.LoginReq;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/2/10 11:32
 **/
@SuppressWarnings("rawtypes")
@RestController
public class LoginController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Value(value = "${front.login.url}")
    private String frontLoginUrl;

    @PostMapping("/login")
    public HttpResultVo<LoginReq> login(@RequestBody LoginReq loginReq) {
        String loginName = loginReq.getLoginName();
        String password = loginReq.getPassword();

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();

        //自己系统的密码加密方式 ,这里简单示例一下MD5
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, md5Password);

        try {
            //进行验证，AuthenticationException可以catch到,但是AuthorizationException因为我们使用注解方式,是catch不到的,所以后面使用全局异常捕抓去获取
            subject.login(usernamePasswordToken);

            Serializable token = SecurityUtils.getSubject().getSession().getId();
            loginReq.setToken(token.toString());
            redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(loginReq), 3600 * 24, TimeUnit.SECONDS);
            LOGGER.info("生成单点登录token：{}，并放入redis中", token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return HttpResultUtil.build(
                    HttpStatusEnum.HTTP_400.getCode() ,
                    "用户名或密码错误！");
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return HttpResultUtil.build(
                    HttpStatusEnum.HTTP_403.getCode() ,
                    HttpStatusEnum.HTTP_403.getMessage());
        }
        return HttpResultUtil.build(
                HttpStatusEnum.HTTP_200.getCode() ,
                HttpStatusEnum.HTTP_200.getMessage() , loginReq);
    }

    @GetMapping(value = "toLogin")
    public void toLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(frontLoginUrl);
    }

    @GetMapping("/logout")
    public HttpResultVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return HttpResultUtil.build(HttpStatusEnum.HTTP_200.getCode()
                , HttpStatusEnum.HTTP_200.getMessage());
    }

}
