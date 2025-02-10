package org.chdzq.system.query.model;

import lombok.Data;
import lombok.Value;

/**
 * 字典项
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 23:38
 */
@Data
public class DictionaryItemVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 字典项名称
     */
    private String key;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

}
