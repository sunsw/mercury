package com.sunsw.mercury.service;

import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.generic.GenericService;
import com.sunsw.mercury.model.SysUser;

import java.util.List;

/**
 * 用户 业务 接口
 *
 * @author sunsw
 **/
public interface SysUserService extends GenericService<SysUser, Long> {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser selectByUsername(String username);

    /**
     * 根据组织Id查询用户
     *
     * @param orgId
     * @return
     */
    List<SysUser> selectByOrg(long orgId);

    /**
     * 修改密码
     *
     * @param userId
     * @param psw
     * @return
     */
    int updatePsw(long userId, String psw);

    /**
     * 查询
     *
     * @param user
     * @param pageInfo
     * @return
     */
    PageInfo<SysUser> selectModels(SysUser user, PageInfo<SysUser> pageInfo);

}
