package org.chdzq.common.core.result;

import org.chdzq.common.core.exceptions.BusinessException;
import org.springframework.util.StringUtils;

/**
 * @Description 响应常量
 * @Author chdzq
 * @Date 2024/11/14
 */
public enum ResultError implements IResultError {
    /**
     * 成功
     */
    SUCCESS(0, "请求成功"),

    /**
     * 服务器异常
     */
    SERVER_ERROR(500, "服务器异常"),
    /**
     * 服务器异常
     */
    PARAMETER_ERROR(501, "参数校验失败"),

    /**
     * 被禁止
     */
    FORBIDDEN_ERROR(443, ""),

    /**
     * 未授权的
     */
    AUTHORIZED_ERROR(400, "访问权限异常"),

    ACCESS_TOKEN_ERROR(401, "Token异常，访问未授权"),
    ;

    private Integer code;

    private String message;

    ResultError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
