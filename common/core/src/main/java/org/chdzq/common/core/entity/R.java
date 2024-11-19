package org.chdzq.common.core.entity;


import org.chdzq.common.core.constants.CommonResponseCodeConstant;

import java.io.Serializable;

/**
 * @Description 通用响应结果
 * @Author chdzq
 * @Date 2024/11/14
 */
public final class R<T> implements Serializable {

    private final Integer code;
    private final String message;

    private final T data;

    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return ok(data, null);
    }

    public static <T> R<T> ok(T data, String message) {
        return new R<>(CommonResponseCodeConstant.SUCCESS, message, data);
    }

    public static <T> R<T> fail() {
        return fail(null);
    }

    public static <T> R<T> fail(String message) {
        return fail(null, message);
    }

    public static <T> R<T> fail(T data) {
        return fail(data, null);
    }

    public static <T> R<T> fail(T data, String message) {
        return new R<>(CommonResponseCodeConstant.SERVER_ERROR, message, data);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return new R<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public  Boolean isError() {
        return !isSuccess();
    }

    public Boolean isSuccess() {
        return CommonResponseCodeConstant.SUCCESS == getCode();
    }
}
