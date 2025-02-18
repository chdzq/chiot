package org.chdzq.system.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.chdzq.common.core.ddd.BaseEntity;
import org.chdzq.common.core.ddd.IBaseEntity;
import org.chdzq.common.core.enums.StatusEnum;

/**
 * 部门
 *
 * @author chdzq
 * @create 2025/2/18 18:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Department extends BaseEntity implements IBaseEntity<Long> {
    /**
     * 主键
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门编号
     */
    private String code;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 状态(1:正常;0:禁用)
     */
    private StatusEnum status;

    @Override
    public Long getIdentifier() {
        return id;
    }

    public Department() {
    }

    @Builder
    public Department(Long id,
                      String name,
                      String code,
                      Long parentId,
                      Integer sort,
                      Integer status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parentId = parentId;
        this.sort = sort;
        this.status = StatusEnum.getByCode(status);
        this.setDirty(true);
    }
}
