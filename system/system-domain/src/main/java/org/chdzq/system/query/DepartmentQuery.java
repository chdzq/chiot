package org.chdzq.system.query;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.validation.InEnum;

/**
 * 部门查询
 *
 * @author chdzq
 * @create 2025/2/18 18:53
 */
@Value
public class DepartmentQuery {
    /**
     * 关键字 部门名称或是编码
     */
    String keyword;

    /**
     * 状态(1:正常;0:禁用)
     */
    StatusEnum status;

    @Builder
    public DepartmentQuery(
            String keyword,
            @InEnum(StatusEnum.class)
            Integer status) {
        this.keyword = keyword;
        this.status = StatusEnum.getByCode(status);
    }
}
