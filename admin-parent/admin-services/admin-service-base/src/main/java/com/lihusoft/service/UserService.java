package com.lihusoft.service;

import com.lihusoft.domain.TbUser;
import com.lihusoft.http.HttpResultVo;
import com.lihusoft.request.UserRequest;
import com.lihusoft.response.TbUserResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 描述：
 * 用户服务
 *
 * @author tsc
 * @since 2023/3/28 14:22
 **/
public interface UserService {

    /**
     * 查询用户雷彪
     * @return HttpResultVo
     */
    @GetMapping("/user/list")
    HttpResultVo<List<TbUser>> getUserList();

    /**
     * 根据用户名查询
     * @param tbUser
     *      用户
     * @return List<TbUser>
     */
    @PostMapping("/user/ByName")
    List<TbUserResp> getUserByName(@RequestBody UserRequest tbUser);
}
