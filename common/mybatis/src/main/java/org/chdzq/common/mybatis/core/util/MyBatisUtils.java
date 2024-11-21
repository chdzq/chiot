package org.chdzq.common.mybatis.core.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.chdzq.common.core.entity.PageParam;
import org.chdzq.common.mybatis.core.pojo.OrderField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MyBatis 工具类
 *
 * @author chdzq
 * @create 2022/11/11
 */
public class MyBatisUtils {

    public static <T> Page<T> buildPage(PageParam pageParam) {
        return buildPage(pageParam, null);
    }

    public static <T> Page<T> buildPage(PageParam pageParam, Collection<OrderField> sortFields) {
        // 页码 + 数量
        Page<T> page = new Page<>(pageParam.getPageNo().getPage(), pageParam.getPageSize().getSize());
        // 排序字段
        if (CollectionUtils.isNotEmpty(sortFields)) {
            List<OrderItem> orderItems = sortFields.stream()
                    .map(f -> f.isAsc() ? OrderItem.asc(f.getField()) : OrderItem.desc(f.getField()))
                    .collect(Collectors.toList());
            page.addOrder(orderItems);
        }
        return page;
    }

    /**
     * 将拦截器添加到链中
     * 由于 MybatisPlusInterceptor 不支持添加拦截器，所以只能全量设置
     *
     * @param interceptor 链
     * @param inner 拦截器
     * @param index 位置
     */
    public static void addInterceptor(MybatisPlusInterceptor interceptor, InnerInterceptor inner, int index) {
        List<InnerInterceptor> inners = new ArrayList<>(interceptor.getInterceptors());
        inners.add(index, inner);
        interceptor.setInterceptors(inners);
    }
}
