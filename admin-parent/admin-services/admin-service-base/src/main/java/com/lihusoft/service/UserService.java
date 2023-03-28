package com.lihusoft.service;

import com.lihusoft.domain.TbUser;
import com.lihusoft.http.HttpResultVo;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 描述：
 *      用户服务
 * @author tsc
 * @since 2023/3/28 14:22
 **/
public interface UserService {

    @GetMapping("/user/list")
    HttpResultVo<List<TbUser>> getUserList();
}
