package org.chdzq.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.chdzq.common.core.ddd.BaseEntity;
import org.chdzq.common.core.ddd.IAggregateRoot;
import org.chdzq.common.core.ddd.IBaseEntity;
import org.chdzq.common.core.enums.StatusEnum;

import java.util.List;

/**
 * 字典
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/8 16:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Dictionary extends BaseEntity implements IBaseEntity<Long> {

    /**
     * 主键
     */
    private Long id;

    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 状态(1:正常;0:禁用)
     */
    private StatusEnum status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 字典项列表
     */
    private List<DictionaryItem> items;

    @Override
    public Long getIdentifier() {
        return id;
    }
}
