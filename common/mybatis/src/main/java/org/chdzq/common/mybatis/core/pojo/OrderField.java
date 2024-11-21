package org.chdzq.common.mybatis.core.pojo;

/**
 * 排序字段
 * @author chdzq
 * @create 2024/11/20
 */
import org.chdzq.common.mybatis.core.enums.Sort;

import java.io.Serializable;

public final class OrderField implements Serializable {
    /**
     * 字段
     */
    private final String field;
    /**
     * 顺序
     */
    private final Sort order;

    public OrderField(String field, Sort order) {
        this.field = field;
        this.order = order;
    }

    static OrderField makeBy(String field, Sort order) {
        return new OrderField(field, order);
    }

    public boolean isAsc () {
        return Sort.ASC.equals(order);
    }

    public String getField() {
        return field;
    }

    public Sort getOrder() {
        return order;
    }
}
