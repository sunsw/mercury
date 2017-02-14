package com.sunsw.mercury.controller;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.entry.DatatableParam;
import com.sunsw.mercury.entry.DatatableResult;
import com.sunsw.mercury.model.SysPermission;
import com.sunsw.mercury.service.SysPermissionService;
import com.sunsw.mercury.statics.ResultEnum;
import com.sunsw.mercury.statics.RouterKey;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 权限控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.PERMISSION)
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @RequestMapping(value = RouterKey.VIEW, method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("systemManage/permission");
    }

    @RequestMapping(value = RouterKey.SAVE, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult savePermission(@Valid SysPermission permission, BindingResult bind) {
        AjaxResult result = new AjaxResult();
        if (null != permission.getId()) {//update
            result.setData(sysPermissionService.update(permission));
        } else {
            if (bind.hasErrors()) {
                List<ObjectError> errorList = bind.getAllErrors();
                String err = "";
                for (ObjectError error : errorList) {
                    err += error.getDefaultMessage() + ";";
                }
                result.setCode(ResultEnum.REQUEST_STATUS_BAD.getCode());
                result.setMessage(err);
                return result;
            }
            try {
                int ret = sysPermissionService.insert(permission);
                if (ret != 1) {
                    result.setInfo(ResultEnum.REQUEST_STATUS_BAD);
                    result.setMessage("操作失败，请检查输入信息！");
                    return result;
                }
            } catch (Exception e) {
                result.setInfo(ResultEnum.REQUEST_STATUS_BAD);
                result.setMessage("操作失败，请检查输入信息！");
                return result;
            }
        }
        return result;
    }

    @RequestMapping(value = RouterKey.DEL, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult removePermission(String ids) {
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            sysPermissionService.delete(Long.parseLong(id));
        }
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getPermission(@PathVariable Long id) {
        return new AjaxResult(sysPermissionService.selectById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public DatatableResult getPermissions(DatatableParam param, SysPermission permission) {
        PageInfo<SysPermission> pageInfo = new PageInfo<>();
        pageInfo.setStartRow(param.getStart());
        pageInfo.setPageSize(param.getLength());
        PageInfo<SysPermission> list = sysPermissionService.selectModels(permission, pageInfo);
        DatatableResult result = new DatatableResult(list.getList());
        result.setDraw(param.getDraw());
        result.setRecordsTotal(list.getTotal());
        result.setRecordsFiltered(list.getTotal());
        return result;
    }

    /**
     * 角色所有权限
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getSignPermissionsByRoleId(Long roleId) {
        return new AjaxResult(sysPermissionService.selectSignPermissionsByRoleId(roleId));
    }

    /**
     * 角色所不具有的权限
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "unsign", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getUnsignPermissionsByRoleId(Long roleId) {
        return new AjaxResult(sysPermissionService.selectUnsignPermissionsByRoleId(roleId));
    }

}
