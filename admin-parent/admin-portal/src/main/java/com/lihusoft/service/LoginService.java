package com.lihusoft.service;

import com.lihusoft.response.TbUserResp;

import java.util.List;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/3/29 16:12
 **/
public interface LoginService {

    List<TbUserResp> getUserByName(String userName);
}
