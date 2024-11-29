package org.chdzq.system.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chdzq.system.repository.po.SystemRoleDO;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;

/**
 * 角色
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:39
 */
@Mapper
public interface SystemRoleMapper extends BaseMapperX<SystemRoleDO> {
}
