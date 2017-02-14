package com.sunsw.mercury.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunsw.mercury.dao.SysUserMapper;
import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.generic.GenericServiceImpl;
import com.sunsw.mercury.model.SysUser;
import com.sunsw.mercury.model.SysUserExample;
import com.sunsw.mercury.service.SysUserRoleService;
import com.sunsw.mercury.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service实现类
 *
 * @author sunsw
 */
@Service
public class SysUserServiceImpl extends GenericServiceImpl<SysUser, Long> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public GenericDao<SysUser, Long> getDao() {
        return sysUserMapper;
    }

    @Override
    public int delete(Long userId) {
        this.sysUserMapper.deleteByPrimaryKey(userId);
        sysUserRoleService.deleteByUserId(userId);//删除用户角色
        return 1;
    }

    @Override
    public SysUser selectByUsername(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        final List<SysUser> list = sysUserMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SysUser> selectByOrg(long orgId) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andOrganizationIdEqualTo(orgId);
        final List<SysUser> list = sysUserMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public int updatePsw(long userId, String psw) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(psw);
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    public PageInfo<SysUser> selectModels(SysUser user, PageInfo<SysUser> pageInfo) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(user.getUsername())) {
            criteria.andUsernameLike("%" + user.getUsername() + "%");
        }
        if (!StringUtils.isEmpty(user.getPhone())) {
            criteria.andPhoneLike("%" + user.getPhone() + "%");
        }
        if (!StringUtils.isEmpty(user.getStatus())) {
            criteria.andStatusEqualTo(user.getStatus());
        }
        PageHelper.offsetPage(pageInfo.getStartRow(), pageInfo.getPageSize());
        List<SysUser> list = sysUserMapper.selectByExample(example);
        return new PageInfo(list);
    }

}
