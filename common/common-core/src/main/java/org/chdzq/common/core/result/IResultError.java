package org.chdzq.common.core.result;

import org.chdzq.common.core.entity.Result;
import org.chdzq.common.core.exceptions.BusinessException;
import org.springframework.util.StringUtils;

/**
 * 响应异常 通用接口
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 14:06
 */
public interface IResultError {

    Integer getCode();

    String getMessage();

    /**
     * 创建异常
     * @param message
     * @return
     */
    default BusinessException makeException(String message) {
        return new BusinessException(getCode(), StringUtils.hasText(message)?message: this.getMessage());
    }


    /**
     * 创建异常响应
     * @param message
     * @return
     */
    default Result<?> makeResultFail(String message) {
        return Result.fail(getCode(), StringUtils.hasText(message)?message: this.getMessage());
    }
}
