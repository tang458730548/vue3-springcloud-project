package com.lihusoft.service;

import com.lihusoft.response.TbModuleResp;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/4/2 9:44
 **/
public interface ModuleService {

    /**
     * 获取模块
     *
     * @return List<TbModuleResp>
     */
    @GetMapping("/module/list")
    List<TbModuleResp> getModuleList();
}
