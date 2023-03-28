package com.lihusoft.controller;

import com.lihusoft.domain.TbUser;
import com.lihusoft.feign.TbUserServiceFeign;
import com.lihusoft.http.HttpResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：
 *      用户控制器
 * @author tsc
 * @since 2023/3/28 14:34
 **/

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private TbUserServiceFeign tbUserServiceFeign;

    @GetMapping("/list1")
    public HttpResultVo<List<TbUser>> list(){
        return tbUserServiceFeign.getUserList();
    }

}
