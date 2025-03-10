package org.chdzq.system.repository.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.repository.po.SystemResourceDO;

import java.util.Collection;
import java.util.List;

/**
 * 资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:42
 */
@Mapper
public interface SystemResourceMapper extends BaseMapperX<SystemResourceDO> {

    /**
     * 根据编码和父节点查询主键
     * @param parentId 父节点
     * @param code 编码
     * @return 主键
     */
    Long selectResourceIdByCode(@Param("parentId")Long parentId,
                                @Param("code")String code);


    /**
     * 根据名称和父节点查询主键
     * @param parentId 父节点
     * @param name 名称
     * @return 主键
     */
    Long selectResourceIdByName(@Param("parentId")Long parentId,
                                @Param("name")String name);

    /**
     * 根据条件查询Entity
     * @param queryWrapper
     * @return
     */
    Resource selectEntityBy(@Param(Constants.WRAPPER) Wrapper<SystemResourceDO> queryWrapper);


    /**
     * 根据 角色Id 查询所有的资源
     * @param roleId 角色id
     * @return
     */
    List<SystemResourceDO> selectByRoleId(@Param("roleId") Long roleId);


    /**
     * 根据角色编码 查询所授权的资源列表
     * @param roleIds 角色列表
     * @return
     */
    List<SystemResourceDO> selectByRoleIds(@Param("roleIds") Collection<Long> roleIds);
}
