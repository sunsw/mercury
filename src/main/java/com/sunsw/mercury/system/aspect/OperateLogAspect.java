package com.sunsw.mercury.system.aspect;

import com.sunsw.mercury.model.BaseModel;
import com.sunsw.mercury.model.SysLoginLog;
import com.sunsw.mercury.model.SysOperateLog;
import com.sunsw.mercury.model.SysUser;
import com.sunsw.mercury.service.SysOperateLogService;
import com.sunsw.mercury.statics.SystemProps;
import com.sunsw.mercury.utils.LogUtils;
import com.sunsw.mercury.utils.RequestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * db操作日志
 * Created by sunsw on 2017/2/12.
 */
@Aspect
@Component
public class OperateLogAspect {

    @Resource
    private SysOperateLogService sysOperateLogService;

    @Pointcut("execution(* com.sunsw.mercury.generic.GenericDao.insert*(..))" +
            "|| execution(* com.sunsw.mercury.generic.GenericDao.update*(..))" +
            "|| execution(* com.sunsw.mercury.generic.GenericDao.delete*(..))")
    public void genericMethod() {
    }

    @Pointcut("execution(* com.sunsw.mercury.dao.*.insert*(..))" +
            "|| execution(* com.sunsw.mercury.dao.*.update*(..))" +
            "|| execution(* com.sunsw.mercury.dao.*.delete*(..))")
    public void mapperMethod() {
    }

    @Before("genericMethod() || mapperMethod()")
    public void addOperateLog(JoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        if (null != args && args.length > 0) {
            Object obj = args[0];
            if (obj instanceof SysOperateLog || obj instanceof SysLoginLog) {//忽略操作日志和登录日志
                return;
            }
            SysOperateLog sysOperateLog = new SysOperateLog();
            SysUser currentUser = RequestUtils.currentUser();
            sysOperateLog.setUserId(currentUser.getId());
            sysOperateLog.setOperator(currentUser.getUsername());
            sysOperateLog.setRequestId(RequestUtils.getRequest().getAttribute("requestId").toString());
            Date date = new Date();

            if (obj instanceof BaseModel) {//新增 or 修改
                BaseModel model = (BaseModel) obj;
                if (null == model.getId()) {
                    model.setCreator(RequestUtils.currentUser().getId());//创建人
                    model.setCreateTime(date);
                    sysOperateLog.setOperationType(SystemProps.OPERATE_CREATE);
                    LogUtils.info(SystemProps.OPERATE_CREATE + "-" + obj.getClass().getName() + "-" + obj.toString());
                } else {
                    model.setModifier(RequestUtils.currentUser().getId());//修改人
                    model.setModifyTime(date);
                    sysOperateLog.setOperationType(SystemProps.OPERATE_MODIFY);
                    LogUtils.info(SystemProps.OPERATE_MODIFY + "-" + obj.getClass().getName() + "-" + obj.toString());
                }
                sysOperateLog.setClazz(obj.getClass().getName());
                sysOperateLog.setParams(obj.toString());
            } else {//删除
                sysOperateLog.setOperationType(SystemProps.OPERATE_REMOVE);
                sysOperateLog.setClazz(jp.toShortString());
                sysOperateLog.setParams(obj.toString());
                LogUtils.info(SystemProps.OPERATE_REMOVE + "-" + jp.toShortString() + "-" + obj.toString());
            }
            sysOperateLog.setCreateTime(date);
            sysOperateLogService.insert(sysOperateLog);
        }
    }

}
