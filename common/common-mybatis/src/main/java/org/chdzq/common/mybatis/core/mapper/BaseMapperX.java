package org.chdzq.common.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.ibatis.annotations.Param;
import org.chdzq.common.core.entity.Page;
import org.chdzq.common.core.ddd.PageQuery;
import org.chdzq.common.mybatis.core.util.MyBatisUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 在 MyBatis Plus 的 BaseMapper 的基础上拓展，提供更多的能力
 * @author chdzq
 * @create 2022/11/11
 */
public interface BaseMapperX<T> extends BaseMapper<T> {

    /**
     * 根据主键查询
     * @param queryWrapper 查询
     * @return
     */
    Serializable isExistedByKey(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据主键列表查询
     * @param queryWrapper 查询
     * @return
     */
    List<Serializable> isExistedByKeys(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);


    @FunctionalInterface
    interface CustomQueryMethod<T extends PageQuery, R> {
        IPage<R> doIt(IPage<R> page, T param);
    }

    /**
     * 查询自定义分页数据。主要用于多表join 查询的。
     * @param param
     * @param consumer
     * @return PageResult<R>
     */
    default <R, P extends PageQuery> Page<R> selectCustomPage(P param, CustomQueryMethod<P, R> consumer) {
        // MyBatis Plus 查询
        IPage<R> mpPage = MyBatisUtils.buildPage(param);
        mpPage = consumer.doIt(mpPage, param);
        List<R> records = mpPage.getRecords();
        if (null == records) {
            records = Collections.emptyList();
        }
        // 转换返回
        return new Page<>(mpPage.getTotal(), records);
    }

    default T selectOne(String field, Object value) {
        return selectOne(new QueryWrapper<T>().eq(field, value));
    }

    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default T selectOne(String field1, Object value1, String field2, Object value2) {
        return selectOne(new QueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default Long selectCount() {
        return selectCount(new QueryWrapper<T>());
    }

    default Long selectCount(String field, Object value) {
        return selectCount(new QueryWrapper<T>().eq(field, value));
    }

    default Long selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList() {
        return selectList(new QueryWrapper<>());
    }

    default List<T> selectList(String field, Object value) {
        return selectList(new QueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(String field, Collection<?> values) {
        return selectList(new QueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(SFunction<T, ?> field, Collection<?> values) {
        return selectList(new LambdaQueryWrapper<T>().in(field, values));
    }

    default void updateBatch(T update) {
        update(update, new QueryWrapper<>());
    }

}
