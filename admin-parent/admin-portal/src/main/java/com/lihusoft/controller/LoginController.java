package com.lihusoft.controller;

import com.lihusoft.constants.HttpStatusEnum;
import com.lihusoft.http.HttpResultUtil;
import com.lihusoft.http.HttpResultVo;
import com.lihusoft.request.LoginReq;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/2/10 11:32
 **/
@RestController
public class LoginController {

    @PostMapping("/login")
    public HttpResultVo<String> login(@RequestBody LoginReq loginReq) {
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

    @GetMapping(value = "toLogin")
    public void toLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://192.168.0.17:8080/");
    }

}
