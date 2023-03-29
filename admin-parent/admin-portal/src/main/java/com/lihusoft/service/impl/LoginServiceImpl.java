package com.lihusoft.service.impl;

import com.lihusoft.feign.TbUserServiceFeign;
import com.lihusoft.response.TbUserResp;
import com.lihusoft.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/3/29 16:13
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private TbUserServiceFeign userServiceFeign;

    @Override
    public List<TbUserResp> getUserByName(String userName) {
        return userServiceFeign.getUserByName(userName);
    }
}
