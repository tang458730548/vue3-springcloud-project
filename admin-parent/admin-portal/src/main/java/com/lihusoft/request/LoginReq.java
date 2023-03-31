package com.lihusoft.request;

import lombok.Data;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/3/29 18:25
 **/
@Data
public class LoginReq {

    private String loginName;

    private String password;

    private String token;
}
