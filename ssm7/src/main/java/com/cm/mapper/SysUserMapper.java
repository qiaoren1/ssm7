package com.cm.mapper;

import com.cm.entity.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser>{

    public SysUser login(SysUser sysUser);

}