package com.sunsw.mercury.service.impl;

import com.sunsw.mercury.dao.SysRolePermissionMapper;
import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.generic.GenericServiceImpl;
import com.sunsw.mercury.model.SysRolePermission;
import com.sunsw.mercury.model.SysRolePermissionExample;
import com.sunsw.mercury.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色权限Service实现类
 *
 * @author sunsw
 */
@Service
public class SysRolePermissionServiceImpl extends GenericServiceImpl<SysRolePermission, Long> implements SysRolePermissionService {

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public GenericDao<SysRolePermission, Long> getDao() {
        return sysRolePermissionMapper;
    }

    @Override
    public int deleteByPermissionId(long permissionId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andPermissionIdEqualTo(permissionId);
        return sysRolePermissionMapper.deleteByExample(example);
    }

    @Override
    public int deleteByRoleId(long roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return sysRolePermissionMapper.deleteByExample(example);
    }

    @Override
    public int deleteByRoleIdAndPermissionId(long roleId, long permissionId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andPermissionIdEqualTo(permissionId);
        return sysRolePermissionMapper.deleteByExample(example);
    }

}
