package org.chdzq.system.query;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.chdzq.common.core.ddd.PageQuery;
import org.chdzq.common.core.enums.ResourceEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.validation.InEnum;

/**
 * 资源分页查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 14:33
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class ResourcePageQuery extends PageQuery {

    /**
     * 名称
     */
    String name;

    /**
     * 编码
     */
    String code;

    /**
     * 菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)
     */
    ResourceEnum type;

    /**
     * 显示状态(1:显示;0:隐藏)
     */
    StatusEnum enabled;

    public ResourcePageQuery(Integer pageNo,
                             Integer pageSize,
                             String name,
                             String code,
                             @InEnum(ResourceEnum.class)
                             Integer type,
                             @InEnum(StatusEnum.class)
                             Integer enabled) {
        super(pageNo, pageSize);
        this.name = name;
        this.code = code;
        this.type = ResourceEnum.getByCode(type);
        this.enabled = StatusEnum.getByCode(enabled);
    }
}
