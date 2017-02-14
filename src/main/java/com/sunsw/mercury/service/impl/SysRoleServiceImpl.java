package com.sunsw.mercury.service.impl;

import com.sunsw.mercury.dao.SysRoleMapper;
import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.generic.GenericServiceImpl;
import com.sunsw.mercury.model.SysRole;
import com.sunsw.mercury.service.SysRoleMenuService;
import com.sunsw.mercury.service.SysRolePermissionService;
import com.sunsw.mercury.service.SysRoleService;
import com.sunsw.mercury.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色Service实现类
 *
 * @author sunsw
 */
@Service
public class SysRoleServiceImpl extends GenericServiceImpl<SysRole, Long> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public GenericDao<SysRole, Long> getDao() {
        return sysRoleMapper;
    }

    @Override
    public int delete(Long roleId) {
        this.sysRoleMapper.deleteByPrimaryKey(roleId);
        sysUserRoleService.deleteByRoleId(roleId);//删除用户角色
        sysRoleMenuService.deleteByRoleId(roleId);//删除角色菜单
        sysRolePermissionService.deleteByRoleId(roleId);//删除角色权限
        return 1;
    }

    @Override
    public List<SysRole> selectSignRolesByUserId(long userId) {
        return sysRoleMapper.selectSignRolesByUserId(userId);
    }

    @Override
    public List<SysRole> selectUnsignRolesByUserId(long userId) {
        return sysRoleMapper.selectUnsignRolesByUserId(userId);
    }

}
