package com.sunsw.mercury.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.dao.SysLoginLogMapper;
import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.generic.GenericServiceImpl;
import com.sunsw.mercury.model.SysLoginLog;
import com.sunsw.mercury.model.SysLoginLogExample;
import com.sunsw.mercury.service.SysLoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志Service实现类
 *
 * @author sunsw
 */
@Service
public class SysLoginLogServiceImpl extends GenericServiceImpl<SysLoginLog, Long> implements SysLoginLogService {

    @Resource
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public GenericDao<SysLoginLog, Long> getDao() {
        return sysLoginLogMapper;
    }

    @Override
    public PageInfo<SysLoginLog> selectModels(SysLoginLog sysLoginLog, PageInfo<SysLoginLog> pageInfo) {
        SysLoginLogExample example = new SysLoginLogExample();
        SysLoginLogExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(sysLoginLog.getUsername())) {
            criteria.andUsernameLike("%" + sysLoginLog.getUsername() + "%");
        }
        example.setOrderByClause("create_time desc");
        PageHelper.offsetPage(pageInfo.getStartRow(), pageInfo.getPageSize());
        List<SysLoginLog> list = sysLoginLogMapper.selectByExample(example);
        return new PageInfo(list);
    }

}
