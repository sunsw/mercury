package com.sunsw.mercury.statics;

/**
 * 系统代码
 * Created by sunsw on 2016/4/1.
 */
public enum ResultEnum {

    //0 ~ 100000 系统错误
    UNKNOWN("000000", "系统未知错误"),
    SYSTEM_ERROR("000001", "系统错误"),
    DATABASE_ERROR("000002", "数据库错误"),
    NOT_PERMITTED("000003", "不予许访问"),
    UNAUTHORIZED("000004", "未授权的访问"),
    BEAN_VALIDATION_ERROR("000005", "数据校验错误"),
    PARAMETER_NOT_NULL("000006", "参数不能为空"),

    //100000 ~ 200000 业务错误码
    REQUEST_STATUS_OK("100000", "SUCCESS"),
    REQUEST_STATUS_BAD("100001", "FAILURE"),
    REQUEST_STATUS_NOT_LOGIN("100002", "NOT LOGIN"),
    USER_STATUS_ENABLE("100003", "Y"),
    USER_STATUS_DISABLE("100004", "N");

    private String code;
    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
