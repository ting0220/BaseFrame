package com.example.zhaoting.baseframe.netUtils;

import com.example.zhaoting.baseframe.bean.ErrorBean;

/**
 * Created by zhaoting on 16/7/28.
 */
public class ApiException extends RuntimeException {

    public ApiException(ErrorBean bean) {
        this(getApiExceptionMessage(bean));
    }

    public ApiException(String message) {
        super(message);
    }

    private static String getApiExceptionMessage(ErrorBean bean) {
        return bean.getField() + bean.getMessage();
    }
}
