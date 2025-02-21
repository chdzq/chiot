package org.chdzq.common.core.dictionary;

import lombok.Data;

import java.io.Serializable;

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
    private Serializable key;

    /**
     * 字典项值
     */
    private String value;
}
