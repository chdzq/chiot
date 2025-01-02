package org.chdzq.system.service;

import org.chdzq.common.core.entity.Page;
import org.chdzq.system.query.RolePageQuery;
import org.chdzq.system.query.model.RoleVO;

/**
 * 角色查询服务器
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 17:34
 */
public interface RoleQueryService {

    /**
     * 查询列表数据
     * @param param 查询参数
     * @return 分页数据
     */
    Page<? extends RoleVO> page(RolePageQuery param);
}
