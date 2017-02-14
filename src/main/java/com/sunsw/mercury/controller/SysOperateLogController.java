package com.sunsw.mercury.controller;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.entry.DatatableParam;
import com.sunsw.mercury.entry.DatatableResult;
import com.sunsw.mercury.model.SysOperateLog;
import com.sunsw.mercury.service.SysOperateLogService;
import com.sunsw.mercury.statics.RouterKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 操作日志控制器
 *
 * @author sunsw
 **/
@Controller
@RequestMapping(RouterKey.OPERATE_LOG)
public class SysOperateLogController {

    @Resource
    private SysOperateLogService sysOperateLogService;

    @RequestMapping(value = RouterKey.VIEW, method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("systemManage/operateLog");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public DatatableResult getOperateLogs(DatatableParam param, SysOperateLog sysOperateLog) {
        PageInfo<SysOperateLog> pageInfo = new PageInfo<>();
        pageInfo.setStartRow(param.getStart());
        pageInfo.setPageSize(param.getLength());
        PageInfo<SysOperateLog> list = sysOperateLogService.selectModels(sysOperateLog, pageInfo);
        DatatableResult result = new DatatableResult(list.getList());
        result.setDraw(param.getDraw());
        result.setRecordsTotal(list.getTotal());
        result.setRecordsFiltered(list.getTotal());
        return result;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getOperateLogs0() {
        return new AjaxResult(sysOperateLogService.selectById(1L));
    }

}
