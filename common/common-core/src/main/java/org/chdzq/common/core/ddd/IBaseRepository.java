package org.chdzq.common.core.ddd;

import java.io.Serializable;

/**
 * 基础数据仓储
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:01
 */
public interface IBaseRepository<T extends IAggregateRoot<ID>, ID extends Serializable> {
    /**
     * 创建
     * @param entity 实体
     */
    void create(T entity);

    /**
     * 更新
     * @param entity 实体
     */
    void update(T entity);

    /**
     * 删除
     * @param entity 实体
     */
    void delete(T entity);

}
