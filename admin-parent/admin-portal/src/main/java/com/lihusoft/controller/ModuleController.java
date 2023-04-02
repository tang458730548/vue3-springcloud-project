package com.lihusoft.controller;

import com.lihusoft.feign.TbModuleServiceFeign;
import com.lihusoft.http.HttpResultUtil;
import com.lihusoft.http.HttpResultVo;
import com.lihusoft.response.TbModuleResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/4/2 9:42
 **/
@RestController
@RequestMapping("/admin/module")
public class ModuleController {

    @Resource
    private TbModuleServiceFeign moduleServiceFeign;

    @PostMapping("/list")
    HttpResultVo<List<TbModuleResp>> getModuleList(){
        return HttpResultUtil.build(moduleServiceFeign.getModuleList());
    }

}
