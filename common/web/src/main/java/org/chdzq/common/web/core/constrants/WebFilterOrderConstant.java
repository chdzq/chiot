package org.chdzq.common.web.core.constrants;

/**
 * Web 过滤器顺序的枚举类，保证过滤器按照符合我们的预期
 * @author chdzq
 * @version 1.0
 * @date 2024/11/21 23:31
 */
public interface WebFilterOrderConstant {

    int CORS_FILTER = Integer.MIN_VALUE;

    int USER_FILTER = Integer.MIN_VALUE + 500;
}
