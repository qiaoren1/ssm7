package com.cm.service.impl;

import com.cm.entity.SysUser;
import com.cm.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * Created by Lenovo on 2020/5/8.
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService{

    @Override
    public SysUser login(SysUser sysUser) {
        return sysUserMapper.login(sysUser);
    }



}
