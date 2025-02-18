package org.chdzq.common.core.dictionary;

import org.chdzq.common.core.entity.Tuple2;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author chdzq
 * @create 2022/12/22
 */
public interface DictionaryConstantService {

    /**
     * 根据别名获取字典表常量
     * @param aliasName 别名
     * @return 列表
     */
    List<DictionaryConstantItem> getDictionaryConstantByAliasName(String aliasName);

    /**
     * 查询常量字典表列表
     * @return 常量字典列表
     */
    List<DictionaryConstant> getDictionaryConstantList();

}
