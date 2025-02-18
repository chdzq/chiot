package org.chdzq.common.core.dictionary;

import lombok.Data;

/**
 * 常量字典项
 *
 * @author chdzq
 * @create 2025/2/14 09:40
 */
@Data
public class DictionaryConstantItem {
    /**
     * 字典项名称
     */
    private String key;

    /**
     * 字典项值
     */
    private String value;
}
