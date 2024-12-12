package org.chdzq.system.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chdzq.system.repository.po.SystemUserRoleDO;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;

/**
 * 系统用户角色关联表
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 11:40
 */
@Mapper
public interface SystemUserRoleMapper extends BaseMapperX<SystemUserRoleDO> {
}
