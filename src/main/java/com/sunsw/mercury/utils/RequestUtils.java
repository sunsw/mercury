package com.sunsw.mercury.utils;

import com.sunsw.mercury.model.SysUser;
import com.sunsw.mercury.statics.SystemProps;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sucre on 2016/12/29.
 */
public class RequestUtils {

    public static HttpServletRequest getRequest() {
        Object obj = RequestContextHolder.getRequestAttributes();
        return null == obj ? null : ((ServletRequestAttributes) obj).getRequest();
    }

    public static SysUser currentUser() {
        HttpServletRequest request = RequestUtils.getRequest();
        return null == request ? null : (SysUser) request.getSession().getAttribute(SystemProps.CURRENT_USER);
    }
}
