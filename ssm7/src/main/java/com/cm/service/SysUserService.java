package com.cm.service;

import com.cm.entity.SysUser;

/**
 * Created by Lenovo on 2020/5/8.
 */
public interface SysUserService extends BaseService<SysUser>{

    public SysUser login(SysUser sysUser);


}
