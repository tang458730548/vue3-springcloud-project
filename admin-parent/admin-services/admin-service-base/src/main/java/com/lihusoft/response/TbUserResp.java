package com.lihusoft.response;

import lombok.Data;

import java.util.Date;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/3/29 16:22
 **/
@Data
public class TbUserResp {

    private Long id;

    private String name;

    private String loginName;

    private String password;

    private String permissionsName;

    private Long roleId;

    private String roleName;

    private Long permissionId;

    private String perRemarks;

    private Date createTime;

    private Date lastUpdateTime;
}
