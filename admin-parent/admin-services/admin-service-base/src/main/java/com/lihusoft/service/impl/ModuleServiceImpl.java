package com.lihusoft.service.impl;

import com.lihusoft.domain.TbModule;
import com.lihusoft.domain.TbModuleExample;
import com.lihusoft.mapper.TbModuleMapper;
import com.lihusoft.request.ModuleRequest;
import com.lihusoft.response.TbModuleResp;
import com.lihusoft.service.ModuleService;
import com.lihusoft.util.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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

    @Resource
    private SnowFlake snowFlake;

    @Override
    public List<TbModuleResp> list() {
        TbModuleExample tbModuleExample = new TbModuleExample();
        tbModuleExample.setOrderByClause(" MODULE_SORT ASC ");
        List<TbModule> tbModules = tbModuleMapper.selectByExample(tbModuleExample);
        return tbModules.stream().map(f -> {
            TbModuleResp tbModuleResp = new TbModuleResp();
            BeanUtils.copyProperties(f, tbModuleResp);
            return tbModuleResp;
        }).collect(Collectors.toList());
    }

    @Override
    public Integer insert(@RequestBody ModuleRequest moduleRequest) {
        Long id = moduleRequest.getId();
        TbModule tbModule = new TbModule();
        BeanUtils.copyProperties(moduleRequest, tbModule);
        if (id != null) {
            tbModule.setLastUpdateTime(new Date());
            return tbModuleMapper.updateByPrimaryKeySelective(tbModule);
        } else {
            tbModule.setId(snowFlake.nextId());
            return tbModuleMapper.insertSelective(tbModule);
        }
    }

    @Override
    public Integer delete(@PathVariable String id) {
        List<Long> list = new ArrayList<>();
        if(StringUtils.isNotBlank(id)){
            String[] ids = id.split(",");
            for(String sid : ids){
                list.add(Long.parseLong(sid));
            }
        }
        TbModuleExample tbModuleExample = new TbModuleExample();
        tbModuleExample.createCriteria().andIdIn(list);
        return tbModuleMapper.deleteByExample(tbModuleExample);
    }
}
