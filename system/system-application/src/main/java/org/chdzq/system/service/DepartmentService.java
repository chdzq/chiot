package org.chdzq.system.service;

import org.chdzq.system.command.DepartmentCreateCommand;
import org.chdzq.system.command.DepartmentDeleteCommand;
import org.chdzq.system.command.DepartmentUpdateCommand;

/**
 * 部门服务类
 *
 * @author chdzq
 * @create 2025/2/18 18:46
 */
public interface DepartmentService {
     /**
      * @param cmd
     */
    void create(DepartmentCreateCommand cmd);

    /**
     * 更新
     * @param cmd
     */
    void update(DepartmentUpdateCommand cmd);

    /**
     * 删除
     * @param cmd
     */
    void delete(DepartmentDeleteCommand cmd);

}
