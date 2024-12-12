package org.chdzq.common.core.validation;

/**
 * 可枚举的数据,主要是InEnum使用
 * @author chdzq
 * @create 2022/11/8
 */
public interface EnumerableValue<T> {

    /**
     * @return Object 数值
     */
     T getEnumerableValue();
}
