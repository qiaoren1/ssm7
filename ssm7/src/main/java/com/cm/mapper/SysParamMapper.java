package com.cm.mapper;

import com.cm.entity.SysParam;

import java.util.List;

public interface SysParamMapper extends BaseMapper<SysParam>{

    List<SysParam> selectList();
}