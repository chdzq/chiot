package org.chdzq.system.repository.impl;

import org.chdzq.common.core.entity.Page;
import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.query.DictionaryItemPageQuery;
import org.chdzq.system.repository.SystemDictionaryItemRepository;
import org.chdzq.system.repository.dao.SystemDictionaryItemMapper;
import org.chdzq.system.repository.po.SystemDictionaryItemDO;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 字典项
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 17:31
 */
@Repository
public class SystemDictionaryItemRepositoryImpl extends ServiceImplX<SystemDictionaryItemMapper, SystemDictionaryItemDO>
    implements SystemDictionaryItemRepository {

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void saveItems(Collection<SystemDictionaryItemDO> items) {
        if (CollectionUtils.isEmpty(items)) {
            return;
        }
        List<SystemDictionaryItemDO> insert = new ArrayList<>(items.size());
        List<SystemDictionaryItemDO> update = new ArrayList<>(items.size());
        for (SystemDictionaryItemDO item : items) {
            if (Objects.isNull(item.getId())) {
                insert.add(item);
            } else {
                update.add(item);
            }
        }
        if (!CollectionUtils.isEmpty(insert)) {
            this.saveBatch(insert);
        }
        if (!CollectionUtils.isEmpty(update)) {
            this.updateBatchById(update);
        }
    }

    @Override
    public void deleteByDictionaryId(Long dictionaryId) {
        remove(
                WrapperX.<SystemDictionaryItemDO>lambdaQuery()
                .eq(SystemDictionaryItemDO::getDictionaryId, dictionaryId)
        );
    }

    @Override
    public void deleteByItemId(Long itemId) {
        removeById(itemId);
    }

    @Override
    public void deleteByItemIds(List<Long> itemIds) {
        removeByIds(itemIds);
    }

    @Override
    public List<SystemDictionaryItemDO> listByDictionaryId(Long dictionaryId) {
        return list(SystemDictionaryItemDO::getDictionaryId, dictionaryId);
    }

    @Override
    public Page<SystemDictionaryItemDO> page(DictionaryItemPageQuery param) {
        return customPage(param, (p, q)-> baseMapper.queryPageList(p, q));
    }
}
