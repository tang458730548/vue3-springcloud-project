package com.lihusoft.response;

import lombok.Data;

import java.util.Date;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/4/2 9:43
 **/
@Data
public class TbModuleResp {

    private Long id;

    private Long parentId;

    private String moduleName;

    private String moduleUrl;

    private Integer moduleSort;

    private Date createTime;

    private Date lastUpdateTime;

}
