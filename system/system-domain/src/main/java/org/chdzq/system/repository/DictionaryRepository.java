package org.chdzq.system.repository;

import org.chdzq.common.core.ddd.IBaseRepository;
import org.chdzq.system.entity.Dictionary;

/**
 * 字典仓库
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 15:45
 */
public interface DictionaryRepository extends IBaseRepository<Dictionary, Long> {

    /**
     * 根据code 判断是否存在
     * @param code
     * @return
     */
    Long getIdByCode(String code);

    /**
     * 根据name 判断是否存在
     * @param name
     * @return
     */
    Long getIdByName(String name);

    /**
     * 根据 字典id 判断是否存在
     * @param id 字典id
     * @return
     */
    Boolean isExistById(Long id);

    /**
     * 根据 字典id 判断key值是否重复
     * @param dictionaryId 字典id
     * @param key key
     * @return
     */
    Boolean isExistByItemKey(Long dictionaryId, String key);
}
