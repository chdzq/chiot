package org.chdzq.common.core.exceptions;

/**
 * @Description 业务错误
 * @Author chdzq
 * @Date 2024/11/15
 */
public class BusinessException extends RuntimeException{

    private final Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
