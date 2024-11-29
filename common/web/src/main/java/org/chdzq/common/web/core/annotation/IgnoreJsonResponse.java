package org.chdzq.common.web.core.annotation;

import java.lang.annotation.*;

/**
 * @Description 忽略统一处理返回值包装，将返回值包装对象
 * @Author chdzq
 * @Date 2024/11/14
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreJsonResponse {
}
