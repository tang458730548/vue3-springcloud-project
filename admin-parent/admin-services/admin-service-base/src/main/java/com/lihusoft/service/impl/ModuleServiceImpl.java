package com.lihusoft.service.impl;

import com.lihusoft.domain.TbModule;
import com.lihusoft.mapper.TbModuleMapper;
import com.lihusoft.response.TbModuleResp;
import com.lihusoft.service.ModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/4/2 9:46
 **/
@RestController
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private TbModuleMapper tbModuleMapper;

    @Override
    public List<TbModuleResp> getModuleList() {
        List<TbModule> tbModules = tbModuleMapper.selectByExample(null);
        return tbModules.stream().map(f -> {
            TbModuleResp tbModuleResp = new TbModuleResp();
            BeanUtils.copyProperties(f, tbModuleResp);
            return  tbModuleResp;
        }).collect(Collectors.toList());
    }
}
