package org.chdzq.common.core.result;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description 通用响应结果
 * @Author chdzq
 * @Date 2024/11/14
 */
public class Result<T> implements Serializable {

    private Integer code;
    private String message;

    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return ok(data, null);
    }

    public static <T> Result<T> ok(T data, String message) {
        return new Result<>(ResultError.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> fail() {
        return fail(ResultError.SERVER_ERROR);
    }

    public static <T> Result<T> fail(String message) {
        return fail(ResultError.SERVER_ERROR.getCode(), message);
    }

    public static <T> Result<T> fail(T data) {
        return fail(data, null);
    }

    public static <T> Result<T> fail(T data, String message) {
        return new Result<>(ResultError.SERVER_ERROR.getCode(), message, data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> fail(IResultError error) {
        return new Result<>(error.getCode(), error.getMessage(), null);
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

    @JsonIgnore
    public  Boolean isError() {
        return !isSuccess();
    }

    @JsonIgnore
    public Boolean isSuccess() {
        return ResultError.SUCCESS.getCode() == getCode();
    }

    public static Boolean isSuccess(Result r) {
        if (Objects.isNull(r)) {
            return false;
        }
        return r.isSuccess();
    }
}
