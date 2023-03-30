package com.lihusoft.service.impl;

import com.lihusoft.domain.TbUser;
import com.lihusoft.domain.TbUserExample;
import com.lihusoft.http.HttpResultUtil;
import com.lihusoft.http.HttpResultVo;
import com.lihusoft.mapper.Ex.TbUserExMapper;
import com.lihusoft.mapper.TbUserMapper;
import com.lihusoft.request.UserRequest;
import com.lihusoft.response.TbUserResp;
import com.lihusoft.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/3/28 14:22
 **/

@RestController
public class UserServiceImpl implements UserService {

    @Resource
    private TbUserMapper tbUserMapper;

    @Resource
    private TbUserExMapper tbUserExMapper;

    @Override
    public HttpResultVo<List<TbUser>> getUserList() {
        List<TbUser> tbUsers = tbUserMapper.selectByExample(new TbUserExample());
        return HttpResultUtil.build(tbUsers);
    }

    @Override
    public List<TbUserResp> getUserByName(@RequestBody UserRequest tbUser) {
        return tbUserExMapper.getUserByName(tbUser);
    }
}
