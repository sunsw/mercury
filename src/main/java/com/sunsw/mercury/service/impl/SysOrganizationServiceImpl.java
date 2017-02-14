package com.sunsw.mercury.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.dao.SysOrganizationMapper;
import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.generic.GenericServiceImpl;
import com.sunsw.mercury.model.SysOrganization;
import com.sunsw.mercury.model.SysOrganizationExample;
import com.sunsw.mercury.service.SysOrganizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织Service实现类
 *
 * @author sunsw
 */
@Service
public class SysOrganizationServiceImpl extends GenericServiceImpl<SysOrganization, Long> implements SysOrganizationService {

    @Resource
    private SysOrganizationMapper sysOrganizationMapper;

    @Override
    public GenericDao<SysOrganization, Long> getDao() {
        return sysOrganizationMapper;
    }

    @Override
    public List<Long> selectSubOrganization(long orgId) {
        List<Long> result = Arrays.asList(orgId);
        List<Long> subMenu = sysOrganizationMapper.selectByParentId(orgId);
        if (null != subMenu && !subMenu.isEmpty()) {
            for (Long sid : subMenu) {
                result.addAll(selectSubOrganization(sid));
            }
        }
        return result;
    }

    @Override
    public Map<String, String> selectOrganizationMap() {
        Map<String, String> result = new HashMap<>();
        List<SysOrganization> list = sysOrganizationMapper.selectByExample(null);
        if (null != list && !list.isEmpty()) {
            for (SysOrganization org : list) {
                result.put(org.getId().toString(), org.getOrgName());
            }
        }
        return result;
    }

    @Override
    public int delete(Long orgId) {
        List<SysOrganization> result = this.selectByParentId(orgId);
        if (null != result && !result.isEmpty()) {
            for (SysOrganization organization : result) {
                this.delete(organization.getId());
            }
        }
        sysOrganizationMapper.deleteByPrimaryKey(orgId);
        return 1;
    }

    @Override
    public List<SysOrganization> selectOrganizationTree(long parentId) {
        List<SysOrganization> result = this.selectByParentId(parentId);
        if (null != result && !result.isEmpty()) {
            for (SysOrganization org : result) {
                org.setSubOrganizations(selectOrganizationTree(org.getId()));
            }
        }
        return result;
    }

    private List<SysOrganization> selectByParentId(long parentId) {
        SysOrganizationExample example = new SysOrganizationExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause("sort");
        return this.sysOrganizationMapper.selectByExample(example);
    }

    @Override
    public PageInfo<SysOrganization> selectByParentId(long parentId, PageInfo<SysOrganization> pageInfo) {
        SysOrganizationExample example = new SysOrganizationExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause("sort");
        PageHelper.offsetPage(pageInfo.getStartRow(), pageInfo.getPageSize());
        List<SysOrganization> list = sysOrganizationMapper.selectByExample(example);
        return new PageInfo(list);
    }
}
