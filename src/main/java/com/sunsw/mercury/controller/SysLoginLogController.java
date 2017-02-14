package com.sunsw.mercury.controller;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.entry.DatatableParam;
import com.sunsw.mercury.entry.DatatableResult;
import com.sunsw.mercury.model.SysLoginLog;
import com.sunsw.mercury.service.SysLoginLogService;
import com.sunsw.mercury.statics.RouterKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 登录日志控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.LOGIN_LOG)
public class SysLoginLogController {

    @Resource
    private SysLoginLogService sysLoginLogService;

    @RequestMapping(value = RouterKey.VIEW, method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("systemManage/loginLog");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public DatatableResult getLoginLogs(DatatableParam param, SysLoginLog sysLoginLog) {
        PageInfo<SysLoginLog> pageInfo = new PageInfo<>();
        pageInfo.setStartRow(param.getStart());
        pageInfo.setPageSize(param.getLength());
        PageInfo<SysLoginLog> list = sysLoginLogService.selectModels(sysLoginLog, pageInfo);
        DatatableResult result = new DatatableResult(list.getList());
        result.setDraw(param.getDraw());
        result.setRecordsTotal(list.getTotal());
        result.setRecordsFiltered(list.getTotal());
        return result;
    }

}
