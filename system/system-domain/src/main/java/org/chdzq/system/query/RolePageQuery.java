package org.chdzq.system.query;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.chdzq.common.core.ddd.PageQuery;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.validation.InEnum;

/**
 * 角色分页查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 17:31
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class RolePageQuery extends PageQuery {


    /**
     * 角色名称
     */
    String name;

    /**
     * 角色编码
     */
    String code;

    /**
     * 角色状态(1-正常；0-停用)
     */
    StatusEnum status;

    @Builder
    public RolePageQuery(Integer pageNo,
                         Integer pageSize,
                         String name,
                         String code,
                         @InEnum(StatusEnum.class)
                         Integer status) {
        super(pageNo, pageSize);
        this.name = name;
        this.code = code;
        this.status = StatusEnum.getByCode(status);
    }
}
