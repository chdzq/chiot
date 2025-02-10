package org.chdzq.system.repository.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.Dictionary;
import org.chdzq.system.entity.DictionaryItem;
import org.chdzq.system.repository.DictionaryRepository;
import org.chdzq.system.repository.SystemDictionaryItemRepository;
import org.chdzq.system.repository.dao.SystemDictionaryMapper;
import org.chdzq.system.repository.po.SystemDictionaryDO;
import org.chdzq.system.repository.po.SystemDictionaryItemDO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 字典仓库类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 16:00
 */
@Repository
@AllArgsConstructor
public class SystemDictionaryRepositoryImpl extends ServiceImplX<SystemDictionaryMapper, SystemDictionaryDO>
        implements DictionaryRepository {

    private SystemDictionaryItemRepository dictionaryItemRepository;

    @Override
    public void create(Dictionary entity) {
        SystemInfraConvertor convertor = SystemInfraConvertor.INSTANCE;
        Assert.isNull(entity.getId(), "创建字典，需要字典主键为空");
        if (entity.isDirty()) {
            SystemDictionaryDO dictionaryDO = convertor.dictionary2SystemDictionaryDO(entity);
            save(dictionaryDO);
        }
        if (CollectionUtils.isEmpty(entity.getItems())) {
            return;
        }

        List<SystemDictionaryItemDO> items = entity.getItems()
                .stream()
                .filter(DictionaryItem::isDirty)
                .map(convertor::dictionaryItem2SystemDictionaryItemDO)
                .toList();
        dictionaryItemRepository.saveItems(items);
    }

    @Override
    public void update(Dictionary entity) {
        SystemInfraConvertor convertor = SystemInfraConvertor.INSTANCE;
        Assert.notNull(entity.getId(), "更新字典，需要字典主键不为空");
        if (entity.isDirty()) {
            SystemDictionaryDO dictionaryDO = convertor.dictionary2SystemDictionaryDO(entity);
            updateById(dictionaryDO);
        }
        if (CollectionUtils.isEmpty(entity.getItems())) {
            return;
        }

        List<SystemDictionaryItemDO> items = entity.getItems()
                .stream()
                .filter(DictionaryItem::isDirty)
                .map(convertor::dictionaryItem2SystemDictionaryItemDO)
                .toList();
        dictionaryItemRepository.saveItems(items);
    }

    @Override
    public void delete(Dictionary entity) {
        if (Objects.isNull(entity.getId())) {
            if (CollectionUtils.isEmpty(entity.getItems())) {
                return;
            }
            dictionaryItemRepository.deleteByItemIds(
                    entity.getItems()
                            .stream()
                            .map(DictionaryItem::getId)
                            .toList()
            );
        } else {
            removeById(entity.getId());
            dictionaryItemRepository.deleteByDictionaryId(entity.getId());
        }
    }

    @Override
    public Long getIdByCode(String code) {
        Long existedByKey = (Long) baseMapper.isExistedByKey(
                WrapperX.<SystemDictionaryDO>lambdaQuery()
                        .eq(SystemDictionaryDO::getCode, code)
        );
        return existedByKey;
    }

    @Override
    public Long getIdByName(String name) {
        Long existedByKey = (Long) baseMapper.isExistedByKey(
                WrapperX.<SystemDictionaryDO>lambdaQuery()
                        .eq(SystemDictionaryDO::getName, name)
        );
        return existedByKey;
    }

    @Override
    public Boolean isExistById(Long id) {
        Long existedByKey = (Long) baseMapper.isExistedByKey(
                WrapperX.<SystemDictionaryDO>lambdaQuery()
                        .eq(SystemDictionaryDO::getId, id)
        );
        return Objects.nonNull(existedByKey);
    }

    @Override
    public Boolean isExistByItemKey(Long dictionaryId, String key) {
        List<SystemDictionaryItemDO> items = dictionaryItemRepository.listByDictionaryId(dictionaryId);
        if (CollectionUtils.isEmpty(items)) {
            return false;
        }

        for (SystemDictionaryItemDO item : items) {
            if (Objects.equals(item.getKey(), key)) {
                return true;
            }
        }
        return false;
    }
}
