package org.chdzq.system.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;
import org.chdzq.system.repository.po.SystemRoleResourceDO;

/**
 * 角色资源关系表
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/4 09:11
 */
@Mapper
public interface SystemRoleResourceMapper extends BaseMapperX<SystemRoleResourceDO> {
}
