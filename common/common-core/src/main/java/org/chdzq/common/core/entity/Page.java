package org.chdzq.common.core.entity;

import java.util.List;
import java.util.function.Function;

/**
 * @Description 分页数据
 * @Author chdzq
 * @Date 2024/11/20
 */
public class Page<T> {
    /**
     * 总数量
     */
    private final Long total;

    /**
     * 列表数据
     */
    private final List<T> list;

    public Page(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getList() {
        return list;
    }

    public Boolean isEmpty() {
        return list.isEmpty();
    }

    public <R> Page<? extends R> map(Function<? super T, ? extends R> mapper) {
        List<? extends R> rList = list.stream().map(mapper).toList();
        return new Page<>(total, rList);
    }
}
