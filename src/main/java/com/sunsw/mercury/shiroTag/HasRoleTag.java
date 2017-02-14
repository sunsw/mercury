package com.sunsw.mercury.shiroTag;

/**
 * <p>Equivalent to {@link org.apache.shiro.web.tags.HasRoleTag}</p>
 */
public class HasRoleTag extends RoleTag {
    protected boolean showTagBody(String roleName) {
        if (isAdmin()) {
            return true;
        }

        return getSubject() != null && getSubject().hasRole(roleName);
    }
}
