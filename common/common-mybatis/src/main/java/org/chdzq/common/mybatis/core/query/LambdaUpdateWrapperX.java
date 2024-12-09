package org.chdzq.common.mybatis.core.query;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * 拓展 MyBatis Plus LambdaUpdateWrapper 类，主要增加如下功能：
 *  1. 拼接条件的方法，增加 xxxIfPresent 方法，用于判断值不存在的时候，不要拼接到条件中。
 * @author chdzq
 * @create 2024/11/20
 */
public class LambdaUpdateWrapperX<T> extends LambdaUpdateWrapper<T> {

    /**
     * 如果val是null就不执行 否则就执行
     * @param column
     * @param val
     * @return
     */
    public LambdaUpdateWrapperX<T> setIfPresent(SFunction<T, ?> column, Object val) {
        if (val == null) {
            return this;
        }
        super.set(column, val);
        return this;
    }

    /**
     * 如果val是null就不执行 否则就执行
     * @param column
     * @param val
     * @param mapping
     * @return
     */
    public LambdaUpdateWrapperX<T> setIfPresent(SFunction<T, ?> column, Object val, String mapping) {
        if (val == null) {
            return this;
        }
        super.set(column, val, mapping);
        return this;
    }


    //重写父类方法，方便链式调用
    @Override
    public LambdaUpdateWrapperX<T> set(SFunction<T, ?> column, Object val) {
        super.set(column, val);
        return this;
    }

    @Override
    public LambdaUpdateWrapperX<T> set(SFunction<T, ?> column, Object val, String mapping) {
        super.set(column, val, mapping);
        return this;
    }

    @Override
    public LambdaUpdateWrapperX<T> set(boolean condition, SFunction<T, ?> column, Object val) {
        super.set(condition, column, val);
        return this;
    }

    public LambdaUpdateWrapper<T> eqIfPresent(SFunction<T, ?> column, Object val) {
        if (val == null) {
            return this;
        }
        super.eq(column, val);
        return this;
    }
}
