package org.chdzq.system.service;

import org.chdzq.system.command.*;

/**
 * 资源服务类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/31 11:17
 */
public interface ResourceService {

    /**
     * 创建
     * @param cmd
     */
    void create(CreateResourceCommand cmd);

    /**
     * 更新
     * @param cmd
     */
    void update(UpdateResourceCommand cmd);

    /**
     * 删除
     * @param cmd
     */
    void delete(DeleteResourceCommand cmd);
}
