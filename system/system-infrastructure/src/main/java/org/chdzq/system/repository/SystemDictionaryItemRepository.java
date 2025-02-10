package org.chdzq.system.repository;

import org.chdzq.common.core.entity.Page;
import org.chdzq.system.query.DictionaryItemPageQuery;
import org.chdzq.system.query.DictionaryPageQuery;
import org.chdzq.system.query.model.DictionaryVO;
import org.chdzq.system.repository.po.SystemDictionaryItemDO;

import java.util.Collection;
import java.util.List;

/**
 * 字典项仓库
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 17:33
 */
public interface SystemDictionaryItemRepository {

    /**
     * 保存字典项
     * @param items
     */
     void saveItems(Collection<SystemDictionaryItemDO> items);

    /**
     * 根据字典ID删除全部字典项
     * @param dictionaryId 字典ID
     */
    void deleteByDictionaryId(Long dictionaryId);

    /**
     * 字根字典项ID删除字典项
     * @param itemId 字典项ID
     */
    void deleteByItemId(Long itemId);

    /**
     * 根据字典项ID列表删除字典像
     * @param itemIds 字典项列表
     */
    void deleteByItemIds(List<Long> itemIds);

    /**
     * 根据字典id 查询字典项列表
     * @param dictionaryId
     * @return
     */
    List<SystemDictionaryItemDO> listByDictionaryId(Long dictionaryId);

    /**
     * 根据条件查询字典集列表
     * @param param 查询条件
     * @return 字典项列表
     */
    Page<SystemDictionaryItemDO> page(DictionaryItemPageQuery param);
}
