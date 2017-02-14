package com.sunsw.mercury.service;

import com.sunsw.mercury.generic.GenericService;
import com.sunsw.mercury.model.SysRole;

import java.util.List;

/**
 * 角色 业务接口
 *
 * @author sunsw
 **/
public interface SysRoleService extends GenericService<SysRole, Long> {

    List<SysRole> selectSignRolesByUserId(long userId);

    List<SysRole> selectUnsignRolesByUserId(long userId);

}
