package org.chdzq.system.service;

import org.chdzq.common.core.entity.Page;
import org.chdzq.system.query.RoleListQuery;
import org.chdzq.system.query.RolePageQuery;
import org.chdzq.system.query.model.RoleVO;
import org.chdzq.system.repository.po.SystemRoleDO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 角色查询服务器
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 17:34
 */
public interface RoleQueryService {

    /**
     * 查询列表数据
     * @param param 查询参数
     * @return 分页数据
     */
    Page<? extends RoleVO> page(RolePageQuery param);

    /**
     * 查询列表数据
     * @param param 查询参数
     * @return 分页数据
     */
    List<? extends RoleVO> list(RoleListQuery param);

    /**
     * 根据角色编码查询所有的角色数据
     * @param roleCodes
     * @return
     */
    List<RoleVO> listByCodes(Collection<String> roleCodes);


    /**
     * 根据角色列表查询所有的角色数据
     * @param ids
     * @return
     */
    List<SystemRoleDO> listByIds(Collection<? extends Serializable> ids);


    /**
     * 根据用户查询角色列表
     * @param userId
     * @return
     */
    List<SystemRoleDO> listByUserId(Long userId);
}
