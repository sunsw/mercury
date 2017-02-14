package com.sunsw.mercury.controller;

import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.model.SysUserRole;
import com.sunsw.mercury.service.SysUserRoleService;
import com.sunsw.mercury.statics.RouterKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户角色控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.USER_ROLE)
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @RequestMapping(value = RouterKey.SAVE, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveUserRole(SysUserRole rolePermission) {
        sysUserRoleService.insert(rolePermission);
        return new AjaxResult(1);
    }

    @RequestMapping(value = RouterKey.DEL, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult removeUserRole(Long userId, Long roleId) {
        sysUserRoleService.deleteByUserIdAndRoleId(userId, roleId);
        return new AjaxResult(1);
    }

}
