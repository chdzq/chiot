package org.chdzq.authentication.repository;

import org.chdzq.authentication.entity.User;
import org.chdzq.common.core.ddd.IBaseRepository;

/**
 * 用户存储
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:59
 */
public interface UserRepository extends IBaseRepository<User, Long> {

    /**
     * 根据用户名查找信息
     * @param username
     * @return
     */
    User findByUsername(String username);
}
