package com.sunsw.mercury.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.dao.SysPermissionMapper;
import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.generic.GenericServiceImpl;
import com.sunsw.mercury.model.SysPermission;
import com.sunsw.mercury.model.SysPermissionExample;
import com.sunsw.mercury.service.SysPermissionService;
import com.sunsw.mercury.service.SysRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限Service实现类
 *
 * @author sunsw
 */
@Service
public class SysPermissionServiceImpl extends GenericServiceImpl<SysPermission, Long> implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public GenericDao<SysPermission, Long> getDao() {
        return sysPermissionMapper;
    }

    public int delete(Long permissionId) {
        this.sysPermissionMapper.deleteByPrimaryKey(permissionId);
        sysRolePermissionService.deleteByPermissionId(permissionId);//删除角色权限
        return 1;
    }

    @Override
    public List<SysPermission> selectSignPermissionsByRoleId(long roleId) {
        return sysPermissionMapper.selectSignPermissionsByRoleId(roleId);
    }

    @Override
    public List<SysPermission> selectUnsignPermissionsByRoleId(long roleId) {
        return sysPermissionMapper.selectUnsignPermissionsByRoleId(roleId);
    }

    @Override
    public PageInfo<SysPermission> selectModels(SysPermission permission, PageInfo<SysPermission> pageInfo) {
        SysPermissionExample example = new SysPermissionExample();
        SysPermissionExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(permission.getPermissionName())) {
            criteria.andPermissionNameLike("%" + permission.getPermissionName() + "%");
        }
        if (!StringUtils.isEmpty(permission.getPermissionSign())) {
            criteria.andPermissionSignLike("%" + permission.getPermissionSign() + "%");
        }
        PageHelper.offsetPage(pageInfo.getStartRow(), pageInfo.getPageSize());
        List<SysPermission> list = sysPermissionMapper.selectByExample(example);
        return new PageInfo(list);
    }

}
