package org.chdzq.common.core.enums;

/**
 * 枚举通用接口
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 14:59
 */
public interface IBaseEnum<T> {

    T getValue();

    String getLabel();
}
