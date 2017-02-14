package com.sunsw.mercury.entry;

import com.sunsw.mercury.utils.RequestUtils;
import com.sunsw.mercury.statics.ResultEnum;

import static com.sunsw.mercury.statics.ResultEnum.REQUEST_STATUS_OK;

/**
 * 通用返回结果
 * Created by sunsw on 2016/7/5.
 */
public class AjaxResult<T> {

    private boolean status = true;
    private String code = REQUEST_STATUS_OK.getCode();
    private String message = REQUEST_STATUS_OK.getMessage();
    private T data;
    private String serverIp;

    public AjaxResult() {
    }

    public AjaxResult(T data) {
        this.data = data;
    }

    public AjaxResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public AjaxResult(boolean status, ResultEnum resultEnum) {
        this.status = status;
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public AjaxResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public AjaxResult(boolean status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public void setInfo(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getServerIp() {
        return null == RequestUtils.getRequest() ? "" : RequestUtils.getRequest().getLocalAddr();
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "status=" + this.isStatus() +
                ", code='" + this.getCode() + '\'' +
                ", message='" + this.getMessage() + '\'' +
                ", data=" + this.getData() +
                ", serverIp='" + this.getServerIp() + '\'' +
                '}';
    }

}
