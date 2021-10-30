package com.cas.message;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 12:21 下午
 * @desc
 */
public abstract class AbstractResponseMessage extends Message{

    private boolean success;
    private String reason;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AbstractResponseMessage() {}

    public AbstractResponseMessage(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }
}
