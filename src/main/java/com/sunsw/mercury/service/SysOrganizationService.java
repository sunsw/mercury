package com.sunsw.mercury.service;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.generic.GenericService;
import com.sunsw.mercury.model.SysOrganization;

import java.util.List;
import java.util.Map;

/**
 * 组织 业务 接口
 *
 * @author sunsw
 **/
public interface SysOrganizationService extends GenericService<SysOrganization, Long> {

    /**
     * 根据组织Id查询所有下属组织Id（包括自身）
     *
     * @param orgId
     * @return
     */
    List<Long> selectSubOrganization(long orgId);

    Map<String, String> selectOrganizationMap();

    PageInfo<SysOrganization> selectByParentId(long parentId, PageInfo<SysOrganization> pageInfo);

    List<SysOrganization> selectOrganizationTree(long parentId);

}
