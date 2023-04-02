package com.lihusoft.mapper;

import com.lihusoft.domain.TbModule;
import com.lihusoft.domain.TbModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbModuleMapper {
    long countByExample(TbModuleExample example);

    int deleteByExample(TbModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbModule record);

    int insertSelective(TbModule record);

    List<TbModule> selectByExample(TbModuleExample example);

    TbModule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbModule record, @Param("example") TbModuleExample example);

    int updateByExample(@Param("record") TbModule record, @Param("example") TbModuleExample example);

    int updateByPrimaryKeySelective(TbModule record);

    int updateByPrimaryKey(TbModule record);
}