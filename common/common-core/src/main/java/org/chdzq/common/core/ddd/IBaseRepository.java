package org.chdzq.common.core.ddd;

import java.io.Serializable;

/**
 * 基础数据仓储
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:01
 */
public interface IBaseRepository<T extends IBaseEntity<ID>, ID extends Serializable> {

    /**
     * 保存
     * @param entity
     */
    void save(T entity);

    /**
     * 删除
     * @param id
     */
    void deleteById(ID id);

}
