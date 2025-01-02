package org.chdzq.system.service;

import org.chdzq.system.query.model.ResourceVO;

import java.util.List;

/**
 * 资源查询服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 14:40
 */
public interface ResourceQueryService {

    /**
     * 查询列表
     * @return 列表结果
     */
    List<ResourceVO> tree();

}
