package org.chdzq.common.core.dictionary;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 被注册类的配置数据
 * @author chdzq
 * @create 2025/2/14
 */
@Data
@Builder
public class DictionaryConstantDefinition {

    /**
     * 注册的类
     */
    private Class<? extends Enum<? extends DictionaryConstantInterface<? extends Serializable>>> clazz;

    /**
     * 需要排除的列表
     */
    private List<? extends Enum<? extends DictionaryConstantInterface<? extends Serializable>>> excludeList;

    /**
     * 别名
     */
    private String aliasName;
}
