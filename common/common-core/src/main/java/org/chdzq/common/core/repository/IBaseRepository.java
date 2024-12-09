package org.chdzq.common.core.repository;

/**
 * 基础数据仓储
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:01
 */
public interface IBaseRepository<T extends IBaseEntity> {

    /**
     * 保存
     * @param entity
     */
    void save(T entity);

    /**
     * 删除
     * @param entity
     */
    void delete(T entity);

    /**
     * 查询
     * @param id
     * @return
     */
    T findById(Long id);
}
