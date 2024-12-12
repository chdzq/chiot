package org.chdzq.system.entity;

import lombok.Data;
import org.chdzq.common.core.ddd.IBaseEntity;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.enums.StatusEnum;

import java.util.List;

/**
 * 角色
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:33
 */
@Data
public class Role implements IBaseEntity<Long> {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 角色状态(1-正常；0-停用)
     */
    private StatusEnum status;

    /**
     * 数据权限
     */
    private DataScopeEnum dataScope;

    /**
     * 资源列表
     */
    private List<Resource> resource;

    @Override
    public Long getIdentifier() {
        return id;
    }
}
