package com.lihusoft.request;

import lombok.Data;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/3/29 19:05
 **/
@Data
public class UserRequest {

    private String loginName;

    private String password;
}
