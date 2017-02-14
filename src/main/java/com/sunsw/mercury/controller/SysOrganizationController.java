package com.sunsw.mercury.controller;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.entry.DatatableParam;
import com.sunsw.mercury.entry.DatatableResult;
import com.sunsw.mercury.model.SysOrganization;
import com.sunsw.mercury.service.SysOrganizationService;
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
import java.util.Map;

/**
 * 用户控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.ORGANIZATION)
public class SysOrganizationController {

    @Resource
    private SysOrganizationService sysOrganizationService;

    @RequestMapping(value = RouterKey.VIEW, method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("systemManage/organization");
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getOrganizationMap() {
        Map<String, String> map = sysOrganizationService.selectOrganizationMap();
        return new AjaxResult(map);
    }

    @RequestMapping(value = RouterKey.SAVE, method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult saveOrganization(@Valid SysOrganization organization, BindingResult bind) {
        AjaxResult result = new AjaxResult();
        if (null != organization.getId()) {//update
            result.setData(sysOrganizationService.update(organization));
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
                int ret = sysOrganizationService.insert(organization);
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
    public AjaxResult removeOrganization(Long id) {
        return new AjaxResult(sysOrganizationService.delete(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getOrganization(@PathVariable Long id) {
        return new AjaxResult(sysOrganizationService.selectById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getOrganizations() {
        List<SysOrganization> Organizations = sysOrganizationService.selectOrganizationTree(-1L);
        return new AjaxResult(Organizations);
    }

    /**
     * 获取子组织
     *
     * @param param
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/sub_organizations", method = RequestMethod.GET)
    @ResponseBody
    public DatatableResult getMenusByParentId(DatatableParam param, Long parentId) {
        PageInfo<SysOrganization> pageInfo = new PageInfo<>();
        pageInfo.setStartRow(param.getStart());
        pageInfo.setPageSize(param.getLength());
        PageInfo<SysOrganization> list = sysOrganizationService.selectByParentId(parentId, pageInfo);
        DatatableResult result = new DatatableResult(list.getList());
        result.setDraw(param.getDraw());
        result.setRecordsTotal(list.getTotal());
        result.setRecordsFiltered(list.getTotal());
        return result;
    }
}
