package com.egdfrm.core.model.common;

import java.util.Objects;

/**
 * Created by tyq on 17/1/5.
 */
public class Json {

    private String message = "系统繁忙,请稍后再试!";
    private boolean success = false;
    private Object result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
