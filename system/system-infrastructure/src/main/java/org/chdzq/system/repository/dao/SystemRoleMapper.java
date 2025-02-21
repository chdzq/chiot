package org.chdzq.system.repository.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;
import org.chdzq.system.query.RoleListQuery;
import org.chdzq.system.query.RolePageQuery;
import org.chdzq.system.repository.po.SystemRoleDO;

import java.util.List;

/**
 * 角色
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:39
 */
@Mapper
public interface SystemRoleMapper extends BaseMapperX<SystemRoleDO> {

    /**
     * 根据编码查询主键
     * @param code 编码
     * @return 主键
     */
    Long selectIdByCode(@Param("code")String code);


    /**
     * 根据名称查询主键
     * @param name 名称
     * @return 主键
     */
    Long selectIdByName(@Param("name")String name);

    /**
     * 根据查询参数分页查询数据
     * @param page
     * @param param
     * @return
     */
    IPage<SystemRoleDO> queryPageList(@Param("page") IPage<SystemRoleDO> page, @Param("param") RolePageQuery param);

    /**
     * 根据查询参数查询数据
     * @param param
     * @return
     */
    List<SystemRoleDO> selectListByQuery(@Param("param") RoleListQuery param);
}
