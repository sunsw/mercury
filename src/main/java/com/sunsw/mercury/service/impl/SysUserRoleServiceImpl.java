package com.sunsw.mercury.service.impl;

import com.sunsw.mercury.dao.SysUserRoleMapper;
import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.generic.GenericServiceImpl;
import com.sunsw.mercury.model.SysUserRole;
import com.sunsw.mercury.model.SysUserRoleExample;
import com.sunsw.mercury.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色权限Service实现类
 *
 * @author sunsw
 */
@Service
public class SysUserRoleServiceImpl extends GenericServiceImpl<SysUserRole, Long> implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public GenericDao<SysUserRole, Long> getDao() {
        return sysUserRoleMapper;
    }

    @Override
    public int deleteByUserId(long userId) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return sysUserRoleMapper.deleteByExample(example);
    }

    @Override
    public int deleteByRoleId(long roleId) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return sysUserRoleMapper.deleteByExample(example);
    }

    @Override
    public int deleteByUserIdAndRoleId(long userId, long roleId) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
        return sysUserRoleMapper.deleteByExample(example);
    }

}
