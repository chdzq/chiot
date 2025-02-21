package org.chdzq.common.core.dictionary.impl;

import lombok.extern.slf4j.Slf4j;
import org.chdzq.common.core.dictionary.*;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.SpringUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

/**
 * 常量字典表
 * @author chdzq
 * @create 2025/2/12
 */
@Slf4j
public class DictionaryConstantServiceImpl implements DictionaryConstantService, CommandLineRunner {

    private static final Map<String, DictionaryConstant> DICTIONARY_CLASS_MAP = new LinkedHashMap<>();

    @Override
    public List<DictionaryConstantItem> getDictionaryConstantByAliasName(String aliasName) {
        DictionaryConstant entity = DICTIONARY_CLASS_MAP.get(aliasName);
        Assert.notNull(entity, "字典常量{}的数据不存在", aliasName);
        if (null != entity.getItems()) {
            return entity.getItems();
        }
        return new ArrayList<>();
    }

    @Override
    public List<DictionaryConstant> getDictionaryConstantList() {
        return new ArrayList<>(DICTIONARY_CLASS_MAP.values());
    }

    @Override
    public void run(String... args) throws Exception {
        //获取字典表常量
        Map<String, DictionaryConstantRegister> beanMap = SpringUtil.getBeansOfType(DictionaryConstantRegister.class);
        beanMap.values().forEach((register)-> {
            Map<String, DictionaryConstantDefinition> store = register.getRegisterStore();
            store.forEach((key, definition)-> {
                DictionaryConstant entity = DICTIONARY_CLASS_MAP.get(key);
                if (null == entity) {
                    Enum<? extends DictionaryConstantInterface<? extends Serializable>>[] constants = definition.getClazz().getEnumConstants();
                    List<DictionaryConstantItem> items = Arrays.stream(constants)
                            .filter((a)-> {
                                if (CollectionUtils.isEmpty(definition.getExcludeList())) {
                                    return true;
                                } else {
                                    return !definition.getExcludeList().contains(a);
                                }})
                            .map((constant) -> {
                                DictionaryConstantInterface<?> dict = (DictionaryConstantInterface<? extends Serializable>) constant;
                                DictionaryConstantItem item = new DictionaryConstantItem();
                                item.setKey(dict.getDictionaryKey());
                                item.setValue(dict.getDictionaryValue());
                                return item;
                            }).toList();
                    entity = new DictionaryConstant(key, items);
                    DICTIONARY_CLASS_MAP.put(key, entity);
                } else {
                    //已经注册过了
                    log.error("重复注册常量字典表：{},forKey:{}", definition, key);
                }
            });
        });

        log.debug("获取字典表常量-{}", DICTIONARY_CLASS_MAP);
    }
}
