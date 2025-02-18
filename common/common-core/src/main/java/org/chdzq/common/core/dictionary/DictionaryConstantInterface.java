package org.chdzq.common.core.dictionary;

import java.io.Serializable;

/**
 * 常量字典表接口
 * @author chdzq
 * @create 2025/2/12
 */
public interface DictionaryConstantInterface<T extends Serializable> {

    /**
     * 字典的key
     * @return 返回常量
     */
    T getDictionaryKey();


    /**
     * 字典的Value
     * @return value值
     */
    String getDictionaryValue();

}
