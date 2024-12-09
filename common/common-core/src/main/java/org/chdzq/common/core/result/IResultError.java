package org.chdzq.common.core.result;

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
}
