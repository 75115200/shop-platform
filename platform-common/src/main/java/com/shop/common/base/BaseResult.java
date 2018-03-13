package com.shop.common.base;

/**
 * 通用的基础返回结果
 */
public class BaseResult {
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;

    /**
     * 成功为1， 失败为0
     */
    private int status;

    /**
     * 附带信息，成功为success，失败为失败原因
     */
    private String message;

    /**
     * 附加信息
     */
    private Object data;

    /**
     * 默认返回成功过结果
     * status = 1
     * message = success
     * @return
     */
    public static BaseResult success() {
        return new BaseResult(SUCCESS, "success");
    }

    /**
     * 返回成功数据
     * @param data 数据
     * @return
     */
    public static BaseResult success(Object data) {
        return new BaseResult(SUCCESS, "success", data);
    }

    /**
     * 返回失败结果
     * @param msg 失败提示信息
     * @return BaseResult
     */
    public static BaseResult fail(String msg) {
        return new BaseResult(FAIL, msg);
    }

    private BaseResult(){}

    private BaseResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private BaseResult(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    private BaseResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
