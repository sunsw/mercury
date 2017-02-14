package com.sunsw.mercury.controller;

import com.sunsw.mercury.entry.AjaxResult;
import com.sunsw.mercury.entry.MercuryException;
import com.sunsw.mercury.statics.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AjaxResult handleCustomException(Exception exception) {
        AjaxResult ajaxResult = new AjaxResult();
        if (exception instanceof MercuryException) {
            MercuryException mercuryException = (MercuryException) exception;
            ajaxResult.setCode(mercuryException.getCode());
            ajaxResult.setMessage(mercuryException.getMessage());
        } else {
            ajaxResult.setCode(ResultEnum.SYSTEM_ERROR.getCode());
            ajaxResult.setMessage(ResultEnum.SYSTEM_ERROR.getMessage());
        }
        return ajaxResult;
    }
}
