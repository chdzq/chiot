package org.chdzq.common.mybatis.core.query;

/**
 * 包装类
 * @author chdzq
 * @date 2024/11/20
 */
public class WrapperX {


    /**
     * Query查询
     * @return
     * @param <T>
     */
    public static <T> QueryWrapperX<T> query() {
        return new QueryWrapperX<>();
    }

    /**
     * lambda查询
     * @return
     * @param <T>
     */
    public static <T> LambdaQueryWrapperX<T> lambdaQuery() {
        return new LambdaQueryWrapperX<>();
    }

    /**
     * lambda更新
     * @return
     * @param <T>
     */
    public static <T> LambdaUpdateWrapperX<T> lambdaUpdate() {
        return new LambdaUpdateWrapperX<>();
    }
}
