package com.lihusoft.mapper.Ex;


import com.lihusoft.domain.TbUser;
import com.lihusoft.response.TbUserResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbUserExMapper {
    List<TbUserResp> getUserByName(@Param("loginName") String loginName);
}