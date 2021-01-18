package com.seckill.dto;

/**
 * 封装json数据结果
 */
public class SeckillResult<T> {
    private boolean success;
    private T data;
    private String error;

    /**
     * 返回成功
     * @param success 表示返回结果
     * @param data 如果返回成功，便会夹带数据返回
     */
    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    /**
     * 返回失败
     * @param success 返回结果
     * @param error 失败原因
     */
    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
