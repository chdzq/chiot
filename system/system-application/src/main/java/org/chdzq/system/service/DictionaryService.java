package org.chdzq.system.service;

import org.chdzq.system.command.*;

/**
 * 字典服务类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 18:20
 */
public interface DictionaryService {
    /**
     * 创建
     * @param cmd
     */
    void create(DictionaryCreateCommand cmd);

    /**
     * 更新
     * @param cmd
     */
    void update(DictionaryUpdateCommand cmd);

    /**
     * 删除
     * @param cmd
     */
    void delete(DictionaryDeleteCommand cmd);

    /**
     * 字典项创建
     * @param cmd
     */
    void create(DictionaryItemCreateCommand cmd);

    /**
     * 字典项更新
     * @param cmd
     */
    void update(DictionaryItemUpdateCommand cmd);

    /**
     * 字典项删除
     * @param cmd
     */
    void delete(DictionaryItemDeleteCommand cmd);
}
