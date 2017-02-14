package com.sunsw.mercury.service;

import com.sunsw.mercury.generic.GenericService;
import com.sunsw.mercury.model.SysUserRole;

/**
 * 用户角色关系 业务接口
 *
 * @author sunsw
 **/
public interface SysUserRoleService extends GenericService<SysUserRole, Long> {

    int deleteByUserId(long userId);

    int deleteByRoleId(long roleId);

    int deleteByUserIdAndRoleId(long userId, long roleId);
}
