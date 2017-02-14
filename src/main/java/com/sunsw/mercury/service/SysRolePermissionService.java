package com.sunsw.mercury.service;

import com.sunsw.mercury.generic.GenericService;
import com.sunsw.mercury.model.SysRolePermission;

/**
 * 角色与权限关系 业务接口
 *
 * @author sunsw
 **/
public interface SysRolePermissionService extends GenericService<SysRolePermission, Long> {

    int deleteByPermissionId(long permissionId);

    int deleteByRoleId(long roleId);

    int deleteByRoleIdAndPermissionId(long roleId, long permissionId);
}
