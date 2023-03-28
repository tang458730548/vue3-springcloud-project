package com.lihusoft.service.impl;

import com.lihusoft.domain.TbUser;
import com.lihusoft.domain.TbUserExample;
import com.lihusoft.http.HttpResultUtil;
import com.lihusoft.http.HttpResultVo;
import com.lihusoft.mapper.TbUserMapper;
import com.lihusoft.service.UserService;
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

    @Override
    public HttpResultVo<List<TbUser>> getUserList() {
        List<TbUser> tbUsers = tbUserMapper.selectByExample(new TbUserExample());
        return HttpResultUtil.build(tbUsers);
    }
}
