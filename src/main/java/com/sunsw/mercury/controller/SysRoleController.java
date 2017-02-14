package com.sunsw.mercury.controller;

import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.model.SysRole;
import com.sunsw.mercury.security.MercuryFilterChainDefinitionsService;
import com.sunsw.mercury.service.SysRoleService;
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
 * 橘色控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.ROLE)
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private MercuryFilterChainDefinitionsService mercuryFilterChainDefinitionsService;

    @RequestMapping(value = RouterKey.VIEW, method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("systemManage/role");
    }

    @RequestMapping(value = RouterKey.SAVE, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveRole(@Valid SysRole role, BindingResult bind) {
        AjaxResult result = new AjaxResult();
        if (null != role.getId()) {//update
            result.setData(sysRoleService.update(role));
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
                int ret = sysRoleService.insert(role);
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
    public AjaxResult removeRole(Long id) {
        sysRoleService.delete(id);
        mercuryFilterChainDefinitionsService.reCreateFilterChains();//重置权限
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getRole(@PathVariable Long id) {
        return new AjaxResult(sysRoleService.selectById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getRoleAll() {
        List<SysRole> list = sysRoleService.selectList();
        return new AjaxResult(list);
    }

    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getSignRolesByUserId(Long userId) {
        return new AjaxResult(sysRoleService.selectSignRolesByUserId(userId));
    }

    @RequestMapping(value = "/unsign", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getUnsignRolesByUserId(Long userId) {
        return new AjaxResult(sysRoleService.selectUnsignRolesByUserId(userId));
    }
}
