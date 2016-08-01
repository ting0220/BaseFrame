package com.example.zhaoting.baseframe.netUtils;

/**
 * Created by zhaoting on 16/7/28.
 */
public class HttpResult<T> {
    /**
     * 全部接口的请求返回遵循统一的返回格式，包含“result”、“data”、“cursor”、“success”、“errors”等部分。查看下表，了解个部分具体说明：
     *
     * result - 包括两种状态：“ok”和“not_ok”，不可为空。
     * data - 返回具体的数据，可以为空。 默认为空Object,存在数据时不一定为Object,有可能会是Array
     * cursor - 返回所包含数据，可以为空。默认为空Object,结构固定
     * success - 成功返回信息，可以为空。默认为空字符"",结构固定
     * errors - 错误返回信息，可以为空。默认空Array,结构固定
     *
     * 注意：其中data和cursor合并为T
     */
    private String result;
    private T data;
    private String success;
    private Object errors;
    private Object cursor;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public Object getCursor() {
        return cursor;
    }

    public void setCursor(Object cursor) {
        this.cursor = cursor;
    }
}
