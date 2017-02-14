package com.sunsw.mercury.security;

import com.sunsw.mercury.utils.PasswordUtils;
import com.sunsw.mercury.model.SysPermission;
import com.sunsw.mercury.model.SysRole;
import com.sunsw.mercury.model.SysUser;
import com.sunsw.mercury.service.SysPermissionService;
import com.sunsw.mercury.service.SysRoleService;
import com.sunsw.mercury.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户身份验证,授权 Realm 组件
 *
 * @author sunsw
 **/
@Component
public class SecurityRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

        final SysUser user = sysUserService.selectByUsername(username);
        final List<SysRole> roleInfos = sysRoleService.selectSignRolesByUserId(user.getId());
        for (SysRole role : roleInfos) {
            // 添加角色
            authorizationInfo.addRole(role.getRoleSign());

            // 添加角色权限
            final List<SysPermission> permissions = sysPermissionService.selectSignPermissionsByRoleId(role.getId());
            for (SysPermission permission : permissions) {
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        final SysUser authentication = sysUserService.selectByUsername(username);
        if (authentication == null || !PasswordUtils.validatePassword(password, authentication.getPassword())) {
            throw new AuthenticationException("Auth failed.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }
}
