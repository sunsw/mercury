package com.sunsw.mercury.controller;

import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.model.SysRolePermission;
import com.sunsw.mercury.service.SysRolePermissionService;
import com.sunsw.mercury.statics.RouterKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 角色权限控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.ROLE_PERMISSION)
public class SysRolePermissionController {

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @RequestMapping(value = RouterKey.SAVE, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveRolePermission(SysRolePermission rolePermission) {
        sysRolePermissionService.insert(rolePermission);
        return new AjaxResult(1);
    }

    @RequestMapping(value = RouterKey.DEL, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult removeRolePermission(Long roleId, Long permissionId) {
        sysRolePermissionService.deleteByRoleIdAndPermissionId(roleId, permissionId);
        return new AjaxResult(1);
    }

}
