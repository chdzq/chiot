package org.chdzq.system.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;

/**
 * 资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:42
 */
@Mapper
public interface SystemResourceMapper extends BaseMapperX<SystemResourceDO> {
}
