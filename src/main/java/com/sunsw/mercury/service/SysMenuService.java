package com.sunsw.mercury.service;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.generic.GenericService;
import com.sunsw.mercury.model.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * 菜单 业务 接口
 *
 * @author sunsw
 **/
public interface SysMenuService extends GenericService<SysMenu, Long> {

    /**
     * 获取角色菜单配置关系for shiro
     *
     * @return
     */
    Map<String, String> selectRoleMenuMap();

    PageInfo<SysMenu> selectByParentId(long parentId, PageInfo<SysMenu> pageInfo);

    List<SysMenu> selectMenuTree(long parentId);

    List<SysMenu> selectMenuByUserId(long userId, long parentId);

    List<SysMenu> selectMenuByRoleId(long roleId, long parentId);
}
