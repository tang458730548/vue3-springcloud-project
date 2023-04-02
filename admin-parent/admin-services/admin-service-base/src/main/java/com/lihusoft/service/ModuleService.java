package com.lihusoft.service;

import com.lihusoft.request.ModuleRequest;
import com.lihusoft.response.TbModuleResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    List<TbModuleResp> list();

    @PostMapping("/module/insert")
    Integer insert(@RequestBody ModuleRequest moduleRequest);

    @PostMapping("/module/delete/{id}")
    Integer delete(@PathVariable String id);

}
