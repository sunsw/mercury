package com.sunsw.mercury.controller;

import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.model.SysRoleMenu;
import com.sunsw.mercury.security.MercuryFilterChainDefinitionsService;
import com.sunsw.mercury.service.SysRoleMenuService;
import com.sunsw.mercury.statics.RouterKey;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 角色菜单控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.ROLE_MENU)
public class SysRoleMenuController {

    @Resource
    private SysRoleMenuService sysRoleMenuService;
    @Resource
    private MercuryFilterChainDefinitionsService mercuryFilterChainDefinitionsService;

    /**
     * 重新分配角色菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult resetRoleMenu(Long roleId, String menuIds) {
        sysRoleMenuService.deleteByRoleId(roleId);
        if (!StringUtils.isEmpty(menuIds)) {
            String[] ids = menuIds.split(",");
            for (String id : ids) {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(Long.parseLong(id));
                sysRoleMenuService.insert(roleMenu);
            }
        }
        mercuryFilterChainDefinitionsService.reCreateFilterChains();//重置权限
        return new AjaxResult(1);
    }

}
