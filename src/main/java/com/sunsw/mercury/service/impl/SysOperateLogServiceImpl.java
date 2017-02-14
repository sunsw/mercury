package com.sunsw.mercury.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.dao.SysOperateLogMapper;
import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.generic.GenericServiceImpl;
import com.sunsw.mercury.model.SysOperateLog;
import com.sunsw.mercury.model.SysOperateLogExample;
import com.sunsw.mercury.service.SysOperateLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志Service实现类
 *
 * @author sunsw
 */
@Service
public class SysOperateLogServiceImpl extends GenericServiceImpl<SysOperateLog, Long> implements SysOperateLogService {

    @Resource
    private SysOperateLogMapper sysOperateLogMapper;

    @Override
    public GenericDao<SysOperateLog, Long> getDao() {
        return sysOperateLogMapper;
    }

    @Override
    public PageInfo<SysOperateLog> selectModels(SysOperateLog sysOperateLog, PageInfo<SysOperateLog> pageInfo) {
        SysOperateLogExample example = new SysOperateLogExample();
        SysOperateLogExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(sysOperateLog.getOperator())) {
            criteria.andOperatorLike("%" + sysOperateLog.getOperator() + "%");
        }
        if (!StringUtils.isEmpty(sysOperateLog.getClazz())) {
            criteria.andClazzLike("%" + sysOperateLog.getClazz() + "%");
        }
        if (!StringUtils.isEmpty(sysOperateLog.getRequestId())) {
            criteria.andRequestIdEqualTo(sysOperateLog.getRequestId());
        }
        example.setOrderByClause("create_time desc");
        PageHelper.offsetPage(pageInfo.getStartRow(), pageInfo.getPageSize());
        List<SysOperateLog> list = sysOperateLogMapper.selectByExample(example);
        return new PageInfo(list);
    }

}
