package org.chdzq.common.core.dictionary;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 常量字典注册器
 * @author chdzq
 * @create 2024/2/14
 */
public class DictionaryConstantRegister {

    private final Map<String, DictionaryConstantDefinition> ENUM_MAP = new LinkedHashMap<>();

    /**
     * 注册一个遵守ConstantDictionaryInterface协议的枚举为常量字典表，
     * 通过接口/dictionary/constant?name={aliasName}查询
     * @param clazz 类
     * @return 字典注册器
     */
    public DictionaryConstantRegister registerConstantEnum(
            Class<? extends Enum<? extends DictionaryConstantInterface<?>>> clazz) {
        return registerConstantEnum(clazz, null, null);
    }

    /**
     * 注册一个遵守ConstantDictionaryInterface协议的枚举为常量字典表，
     * 通过接口/dictionary/constant?name={aliasName}查询
     * @param clazz 类
     * @param aliasName 别名
     * @return 字典注册器
     */
    public DictionaryConstantRegister registerConstantEnum(
            Class<? extends Enum<? extends DictionaryConstantInterface<?>>> clazz,
            String aliasName) {
        return registerConstantEnum(clazz, aliasName, null);
    }

    /**
     * 注册一个遵守ConstantDictionaryInterface协议的枚举为常量字典表，
     * 通过接口/dictionary/constant?name={aliasName}查询
     * @param aliasName 字典表别名
     * @param clazz 类
     * @param excludeList 排除列表
     * @return 字典注册器
     */
    public DictionaryConstantRegister registerConstantEnum(
            Class<? extends Enum<? extends DictionaryConstantInterface<? extends Serializable>>> clazz,
            String aliasName,
            List<? extends Enum<? extends DictionaryConstantInterface<? extends Serializable>>> excludeList) {
        String key;
        if (!StringUtils.hasText(aliasName)) {
            String[] names = StringUtils.split(clazz.getName(), ".");
            if (names != null && names.length > 1) {
                key = names[names.length - 1];
            } else {
                key = clazz.getName();
            }
        } else {
            key = aliasName;
        }
        DictionaryConstantDefinition configuration = DictionaryConstantDefinition.builder().aliasName(aliasName)
                .clazz(clazz)
                .excludeList(excludeList).build();
        ENUM_MAP.put(key, configuration);
        return this;
    }

    public Map<String, DictionaryConstantDefinition> getRegisterStore() {
        return ENUM_MAP;
    }
}
