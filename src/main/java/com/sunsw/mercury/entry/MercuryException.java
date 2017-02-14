package com.sunsw.mercury.entry;

import com.sunsw.mercury.statics.ResultEnum;

public class MercuryException extends Exception {

    private String code;
    private String message;

    public MercuryException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public MercuryException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
