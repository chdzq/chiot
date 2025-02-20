package org.chdzq.system.query;

import lombok.Builder;
import lombok.Value;

/**
 * 根据关键字角色查询列表
 * 关键字可以是编码或是名称
 *
 * @author chdzq
 * @create  2025/2/21 17:31
 */
@Value
public class RoleListQuery {
    /**
     * 关键字
     */
    String keyword;

    @Builder
    public RoleListQuery(String keyword) {
        this.keyword = keyword;
    }
}
