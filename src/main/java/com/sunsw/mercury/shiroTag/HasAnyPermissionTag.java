package com.sunsw.mercury.shiroTag;

/**
 * <p>Equivalent to {@link org.apache.shiro.web.tags.HasPermissionTag}</p>
 *
 * @since 0.1
 */
public class HasAnyPermissionTag extends PermissionTag {
    // Delimeter that separates role names in tag attribute
    private static final String PERMISSION_NAMES_DELIMETER = ",";

    protected boolean showTagBody(String permissionNames) {
        if (isAdmin()) {
            return true;
        }

        boolean hasAnyPermission = false;
        // Iterate through roles and check to see if the user has one of the roles
        for (String p : permissionNames.split(PERMISSION_NAMES_DELIMETER)) {
            if (isPermitted(p.trim())) {
                hasAnyPermission = true;
                break;
            }
        }
        return hasAnyPermission;
    }
}
