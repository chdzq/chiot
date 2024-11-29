package org.chdzq.system.repository;

import org.chdzq.system.entity.User;
import org.chdzq.common.core.repository.IBaseRepository;

/**
 * 用户存储
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:59
 */
public interface UserRepository extends IBaseRepository<User> {

    /**
     * 根据用户名查找信息
     * @param username
     * @return
     */
    User findByUsername(String username);
}
