package com.fly.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fly.system.pojo.entity.SysPermission;

import java.util.List;

public interface ISysPermissionService extends IService<SysPermission> {
    List<SysPermission> getByUserId(Long userId);
}