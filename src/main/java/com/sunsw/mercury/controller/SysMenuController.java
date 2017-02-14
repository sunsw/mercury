package com.sunsw.mercury.controller;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.entry.DatatableParam;
import com.sunsw.mercury.entry.DatatableResult;
import com.sunsw.mercury.model.SysMenu;
import com.sunsw.mercury.model.SysUser;
import com.sunsw.mercury.security.MercuryFilterChainDefinitionsService;
import com.sunsw.mercury.service.SysMenuService;
import com.sunsw.mercury.statics.ResultEnum;
import com.sunsw.mercury.statics.RouterKey;
import com.sunsw.mercury.statics.SystemProps;
import com.sunsw.mercury.utils.RequestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
 * 菜单控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.MENU)
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private MercuryFilterChainDefinitionsService mercuryFilterChainDefinitionsService;

    @RequestMapping(value = RouterKey.VIEW, method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("systemManage/menu");
    }

    @RequestMapping(value = RouterKey.SAVE, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveMenu(@Valid SysMenu menu, BindingResult bind) {
        AjaxResult result = new AjaxResult();
        if (null != menu.getId()) {//update
            result.setData(sysMenuService.update(menu));
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
                int ret = sysMenuService.insert(menu);
                if (ret != 1) {
                    result.setInfo(ResultEnum.REQUEST_STATUS_BAD);
                    result.setMessage("添加失败，请检查输入信息！");
                    return result;
                }
            } catch (Exception e) {
                result.setInfo(ResultEnum.REQUEST_STATUS_BAD);
                result.setMessage("添加失败，请检查输入信息！");
                return result;
            }
        }
        return result;
    }

    @RequestMapping(value = RouterKey.DEL, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult removeMenu(Long id) {
        sysMenuService.delete(id);
        mercuryFilterChainDefinitionsService.reCreateFilterChains();//重置权限
        return new AjaxResult(1);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getMenu(@PathVariable Long id) {
        return new AjaxResult(sysMenuService.selectById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getMenus() {
        List<SysMenu> menus = sysMenuService.selectMenuTree(-1L);
        return new AjaxResult(menus);
    }

    /**
     * 获取子菜单
     *
     * @param param
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/sub_menus", method = RequestMethod.GET)
    @ResponseBody
    public DatatableResult getMenusByParentId(DatatableParam param, Long parentId) {
        PageInfo<SysMenu> pageInfo = new PageInfo<>();
        pageInfo.setStartRow(param.getStart());
        pageInfo.setPageSize(param.getLength());
        PageInfo<SysMenu> list = sysMenuService.selectByParentId(parentId, pageInfo);
        DatatableResult result = new DatatableResult(list.getList());
        result.setDraw(param.getDraw());
        result.setRecordsTotal(list.getTotal());
        result.setRecordsFiltered(list.getTotal());
        return result;
    }

    /**
     * 角色所有菜单
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getMenusByRoleId(Long roleId) {
        List<SysMenu> menus = sysMenuService.selectMenuByRoleId(roleId, -1L);
        return new AjaxResult(menus);
    }

    /**
     * 登录用户所有菜单
     *
     * @return
     */
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getMenusForCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        List<SysMenu> menus;
        if (subject.hasRole(SystemProps.ADMIN_ROLE)) {//如果是admin，则提取所有菜单
            menus = sysMenuService.selectMenuTree(0L);
        } else {
            SysUser sysUser = RequestUtils.currentUser();
            menus = sysMenuService.selectMenuByUserId(sysUser.getId(), 0L);
        }
        return new AjaxResult(menus);
    }

}
