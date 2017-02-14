package com.sunsw.mercury.entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.sunsw.mercury.utils.DateUtils;
import com.sunsw.mercury.utils.LogUtils;
import com.sunsw.mercury.utils.RequestUtils;

/**
 * 日志实体
 * Created by sunsw on 2016/7/5.
 */
public class LogEntry {

    @JSONField(ordinal = 0)
    private String logType = "INFO";
    @JSONField(ordinal = 5)
    private String dataTime;
    @JSONField(ordinal = 10)
    private String methodName;
    @JSONField(ordinal = 15)
    private String message;
    @JSONField(ordinal = 20)
    private boolean alarmSwitch = false;
    @JSONField(ordinal = 25)
    private boolean monitorSwitch = false;
    @JSONField(ordinal = 30)
    private String requestId;
    @JSONField(ordinal = 50)
    private String serverIp;

    public LogEntry(String message) {
        this.message = message;
    }

    public LogEntry(String logType, String message) {
        this.logType = logType;
        this.message = message;
    }

    public LogEntry(String message, boolean alarmSwitch, boolean monitorSwitch) {
        this.message = message;
        this.alarmSwitch = alarmSwitch;
        this.monitorSwitch = monitorSwitch;
    }

    public LogEntry(String logType, String message, boolean alarmSwitch, boolean monitorSwitch) {
        this.logType = logType;
        this.message = message;
        this.alarmSwitch = alarmSwitch;
        this.monitorSwitch = monitorSwitch;
    }

    public String getRequestId() {
        return null == RequestUtils.getRequest() ? "" : RequestUtils.getRequest().getAttribute("requestId").toString();
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMethodName() {
        StackTraceElement[] e = Thread.currentThread().getStackTrace();
        for (int i = 1; i < e.length; ++i) {
            StackTraceElement ste = e[i];
            if (ste.getClassName().equals(LogUtils.class.getName())) {
                return e[i + 1].toString();
            }
        }
        return "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public boolean isAlarmSwitch() {
        return alarmSwitch;
    }

    public void setAlarmSwitch(boolean alarmSwitch) {
        this.alarmSwitch = alarmSwitch;
    }

    public boolean isMonitorSwitch() {
        return monitorSwitch;
    }

    public void setMonitorSwitch(boolean monitorSwitch) {
        this.monitorSwitch = monitorSwitch;
    }

    public String getDataTime() {
        return DateUtils.getToday(DateUtils.FORMAT_ALL_M);
    }

    public String getServerIp() {
        return null == RequestUtils.getRequest() ? "" :RequestUtils.getRequest().getLocalAddr();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
