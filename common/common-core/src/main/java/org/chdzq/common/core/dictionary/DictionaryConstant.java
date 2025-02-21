package org.chdzq.common.core.dictionary;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 常量字典表
 *
 * @author chdzq
 * @create 2025/2/14 09:40
 */
@Data
public class DictionaryConstant {
    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 常量字典项列表
     */
    List<DictionaryConstantItem> items;

    public DictionaryConstant(String code, List<DictionaryConstantItem> items) {
        this.code = code;
        this.items = items;
    }
}
