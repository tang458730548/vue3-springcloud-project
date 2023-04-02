package com.lihusoft.request;

import lombok.Data;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/4/2 16:52
 **/
@Data
public class ModuleRequest {

    private Long id;

    private Long parentId;

    private String moduleName;

    private String moduleUrl;

    private Integer moduleSort;
}
