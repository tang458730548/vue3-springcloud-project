package com.lihusoft.controller;

import com.lihusoft.constants.HttpStatusEnum;
import com.lihusoft.http.HttpResultUtil;
import com.lihusoft.http.HttpResultVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/2/10 11:32
 **/
@RestController
public class LoginController {

    @GetMapping("/login")
    public HttpResultVo<String> login(@RequestParam("userName") String userName,
                                      @RequestParam("password") String password,
                                      @RequestParam("remember") boolean remember) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();

        //自己系统的密码加密方式 ,这里简单示例一下MD5
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, md5Password);
        if(remember){
            usernamePasswordToken.setRememberMe(remember);
        }
        try {
            //进行验证，AuthenticationException可以catch到,但是AuthorizationException因为我们使用注解方式,是catch不到的,所以后面使用全局异常捕抓去获取
            subject.login(usernamePasswordToken);
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
                HttpStatusEnum.HTTP_200.getMessage());
    }
}
