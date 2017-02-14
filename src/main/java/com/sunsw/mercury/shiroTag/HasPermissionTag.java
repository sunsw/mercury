package com.sunsw.mercury.shiroTag;

/**
 * <p>Equivalent to {@link org.apache.shiro.web.tags.HasPermissionTag}</p>
 *
 * @since 0.1
 */
public class HasPermissionTag extends PermissionTag {
    protected boolean showTagBody(String p) {
        if (isAdmin()) {
            return true;
        }

        return isPermitted(p);
    }
}
