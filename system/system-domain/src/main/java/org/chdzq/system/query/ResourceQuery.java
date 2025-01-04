package org.chdzq.system.query;

import lombok.Builder;
import lombok.Value;

/**
 * 资源分页查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 14:33
 */
@Value
@Builder
public class ResourceQuery {

    /**
     * 角色的资源
     */
    Long roleId;
}
