package org.chdzq.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.chdzq.common.core.ddd.BaseEntity;
import org.chdzq.common.core.enums.StatusEnum;

/**
 * 字典项
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 15:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DictionaryItem extends BaseEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 字典主键
     */
    private Long dictionaryId;

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
    private StatusEnum status;

    /**
     * 是否默认(1:是;0:否)
     */
    private Integer defaulted;
}
