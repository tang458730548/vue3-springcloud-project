package com.lihusoft.controller;

import com.lihusoft.feign.TbModuleServiceFeign;
import com.lihusoft.http.HttpResultUtil;
import com.lihusoft.http.HttpResultVo;
import com.lihusoft.request.ModuleRequest;
import com.lihusoft.response.TbModuleResp;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    HttpResultVo<List<TbModuleResp>> list() {
        return HttpResultUtil.build(moduleServiceFeign.list());
    }

    @PostMapping("/insert")
    HttpResultVo<Integer> insert(@RequestBody ModuleRequest moduleRequest) {
        return HttpResultUtil.build(moduleServiceFeign.insert(moduleRequest));
    }

    @PostMapping("/delete/{id}")
    HttpResultVo<Integer> delete(@PathVariable String id) {
        return HttpResultUtil.build(moduleServiceFeign.delete(id));
    }
}
