package com.sunsw.mercury.controller;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.entry.DatatableParam;
import com.sunsw.mercury.entry.DatatableResult;
import com.sunsw.mercury.model.SysUser;
import com.sunsw.mercury.service.SysUserService;
import com.sunsw.mercury.statics.ResultEnum;
import com.sunsw.mercury.statics.RouterKey;
import com.sunsw.mercury.utils.PasswordUtils;
import com.sunsw.mercury.utils.RequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.USER)
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = RouterKey.VIEW, method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("systemManage/user");
    }

    @RequestMapping(value = RouterKey.SAVE, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveUser(@Valid SysUser user, BindingResult bind) {
        AjaxResult result = new AjaxResult();
        if (null != user.getId()) {//update
            result.setData(sysUserService.update(user));
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
                if (StringUtils.isEmpty(user.getPassword())) {
                    user.setPassword("12345678");
                }
                String pwd_hash = PasswordUtils.md5Hex(user.getPassword());
                user.setPassword(pwd_hash);
                user.setStatus(ResultEnum.USER_STATUS_ENABLE.getMessage());
                int ret = sysUserService.insert(user);
                if (ret != 1) {
                    result.setInfo(ResultEnum.REQUEST_STATUS_BAD);
                    result.setMessage("注册失败，请检查输入信息！");
                    return result;
                }
            } catch (Exception e) {
                result.setInfo(ResultEnum.REQUEST_STATUS_BAD);
                result.setMessage("注册失败，请检查输入信息！");
                return result;
            }
        }
        return result;
    }

    @RequestMapping(value = RouterKey.DEL, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult removeUser(String ids) {
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            sysUserService.delete(Long.parseLong(id));
        }
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getUser(@PathVariable Long id) {
        return new AjaxResult(sysUserService.selectById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public DatatableResult getUsers(DatatableParam param, SysUser user) {
        PageInfo<SysUser> pageInfo = new PageInfo<>();
        pageInfo.setStartRow(param.getStart());
        pageInfo.setPageSize(param.getLength());
        PageInfo<SysUser> list = sysUserService.selectModels(user, pageInfo);
        DatatableResult result = new DatatableResult(list.getList());
        result.setDraw(param.getDraw());
        result.setRecordsTotal(list.getTotal());
        result.setRecordsFiltered(list.getTotal());
        return result;
    }

    /**
     * 修改密码
     *
     * @param oldPsw
     * @param newPsw
     * @param confPsw
     * @param request
     * @return logout:未登录 misMatch:新密码两次不匹配 wrongPsw:原密码错误 fail:修改失败 success:修改成功
     */
    @RequestMapping(value = "/psw", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult changePsw(String oldPsw, String newPsw, String confPsw, HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        String msg = "";
        SysUser currentUser = RequestUtils.currentUser();
        if (currentUser == null) {
            msg = "logout";

        } else if (!newPsw.equals(confPsw)) {
            msg = "misMatch";
        } else if (!PasswordUtils.validatePassword(oldPsw, currentUser.getPassword())) {
            msg = "wrongPsw";
        }
        if (!StringUtils.isEmpty(msg)) {
            result.setInfo(ResultEnum.REQUEST_STATUS_BAD);
            result.setMessage(msg);
            return result;
        }
        String newPsw_hash = PasswordUtils.md5Hex(newPsw);
        currentUser.setPassword(newPsw_hash);
        int ret = sysUserService.update(currentUser);
        if (ret != 1) {
            result.setInfo(ResultEnum.REQUEST_STATUS_BAD);
            result.setMessage("fail");
            return result;
        }
        result.setInfo(ResultEnum.REQUEST_STATUS_OK);
        result.setMessage("success");
        return result;
    }

}
