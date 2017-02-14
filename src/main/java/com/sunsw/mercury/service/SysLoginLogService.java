package com.sunsw.mercury.service;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.generic.GenericService;
import com.sunsw.mercury.model.SysLoginLog;

/**
 * 登录日志 业务接口
 *
 * @author sunsw
 **/

public interface SysLoginLogService extends GenericService<SysLoginLog, Long> {

    PageInfo<SysLoginLog> selectModels(SysLoginLog sysLoginLog, PageInfo<SysLoginLog> pageInfo);

}
