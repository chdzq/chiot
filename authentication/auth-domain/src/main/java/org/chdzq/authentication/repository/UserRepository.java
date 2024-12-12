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

    /**
     * 根据主键查询是否存在当前用户
     * @param id 主键
     * @return
     */
    Boolean isExistByKey(Long id);

    /**
     * 根据用户名查询是否存在当前用户
     * @param username 用户名
     * @return
     */
    Boolean isUsernameAvailable(String username);

    /**
     * 根据用户名是否可以使用：username没有被别的用户使用过；或是跟自身保持一致
     * @param id 主键
     * @return
     */
    Boolean isUsernameAvailable(Long id, String username);

}
