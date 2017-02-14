package com.sunsw.mercury.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.dao.SysMenuMapper;
import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.generic.GenericServiceImpl;
import com.sunsw.mercury.model.SysMenu;
import com.sunsw.mercury.model.SysMenuExample;
import com.sunsw.mercury.service.SysMenuService;
import com.sunsw.mercury.service.SysRoleMenuService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 菜单接口实现类
 * <p>
 * Created by sunsw on 2016/7/1.
 */
@Service
public class SysMenuServiceImpl extends GenericServiceImpl<SysMenu, Long> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public GenericDao<SysMenu, Long> getDao() {
        return sysMenuMapper;
    }

    /**
     * shiro 角色权限
     *
     * @return
     */
    @Override
    public Map<String, String> selectRoleMenuMap() {
        Map<String, String> map = new HashedMap();
        List<Map> list = this.sysMenuMapper.selectRoleMenuMap();
        for (Map<String, String> e : list) {
            map.put(e.get("url"), e.get("roles"));
        }
        return map;
    }

    @Override
    public int delete(Long menuId) {
        List<SysMenu> result = this.selectByParentId(menuId);
        if (null != result && !result.isEmpty()) {
            for (SysMenu menu : result) {
                this.delete(menu.getId());
            }
        }
        sysMenuMapper.deleteByPrimaryKey(menuId);
        sysRoleMenuService.deleteByMenuId(menuId);//删除角色菜单
        return 1;
    }

    /**
     * 获取菜单树（parentId=0）
     *
     * @param parentId
     * @return
     */
    @Override
    public List<SysMenu> selectMenuTree(long parentId) {
        List<SysMenu> result = this.selectByParentId(parentId);
        if (null != result && !result.isEmpty()) {
            for (SysMenu menu : result) {
                menu.setSubMenus(selectMenuTree(menu.getId()));
            }
        }
        return result;
    }

    private List<SysMenu> selectByParentId(long parentId) {
        SysMenuExample example = new SysMenuExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause("sort");
        return this.sysMenuMapper.selectByExample(example);
    }

    @Override
    public PageInfo<SysMenu> selectByParentId(long parentId, PageInfo<SysMenu> pageInfo) {
        SysMenuExample example = new SysMenuExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause("sort");
        PageHelper.offsetPage(pageInfo.getStartRow(), pageInfo.getPageSize());
        List<SysMenu> list = sysMenuMapper.selectByExample(example);
        return new PageInfo(list);
    }

    /**
     * 根据userId获取菜单树（parentId=0）
     *
     * @param userId
     * @param parentId
     * @return
     */
    @Override
    public List<SysMenu> selectMenuByUserId(long userId, long parentId) {
        List<SysMenu> result = this.sysMenuMapper.selectByUserIdAndParentId(userId, parentId);
        if (null != result && !result.isEmpty()) {
            for (SysMenu menu : result) {
                menu.setSubMenus(selectMenuByUserId(userId, menu.getId()));
            }
        }
        return result;
    }

    @Override
    public List<SysMenu> selectMenuByRoleId(long roleId, long parentId) {
        List<SysMenu> result = this.sysMenuMapper.selectByRoleIdAndParentId(roleId, parentId);
        if (null != result && !result.isEmpty()) {
            for (SysMenu menu : result) {
                menu.setSubMenus(selectMenuByRoleId(roleId, menu.getId()));
            }
        }
        return result;
    }

}
