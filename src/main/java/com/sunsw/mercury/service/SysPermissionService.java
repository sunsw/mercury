package com.sunsw.mercury.service;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.generic.GenericService;
import com.sunsw.mercury.model.SysPermission;

import java.util.List;

/**
 * 权限 业务接口
 *
 * @author sunsw
 **/

public interface SysPermissionService extends GenericService<SysPermission, Long> {

    List<SysPermission> selectSignPermissionsByRoleId(long roleId);

    List<SysPermission> selectUnsignPermissionsByRoleId(long roleId);

    PageInfo<SysPermission> selectModels(SysPermission permission, PageInfo<SysPermission> pageInfo);

}
