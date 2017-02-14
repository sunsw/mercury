package com.sunsw.mercury.service;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.generic.GenericService;
import com.sunsw.mercury.model.SysOperateLog;

/**
 * 操作日志 业务接口
 *
 * @author sunsw
 **/

public interface SysOperateLogService extends GenericService<SysOperateLog, Long> {

    PageInfo<SysOperateLog> selectModels(SysOperateLog sysOperateLog, PageInfo<SysOperateLog> pageInfo);

}
