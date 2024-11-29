package org.chdzq.system.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chdzq.system.entity.User;
import org.chdzq.system.repository.po.SystemUserDO;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;

/**
 * 用户
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:38
 */
@Mapper
public interface SystemUserMapper extends BaseMapperX<SystemUserDO> {

    /**
     * 根据用户名获取认证信息
     * @param username 用户名
     * @return
     */
    User selectUserAuthInfo(String username);
}
