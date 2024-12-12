package org.chdzq.authentication.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.chdzq.authentication.entity.Role;
import org.chdzq.authentication.repository.SystemUserRoleRepository;
import org.chdzq.authentication.repository.dao.SystemUserRoleMapper;
import org.chdzq.authentication.repository.po.SystemUserRoleDO;
import org.chdzq.common.mybatis.core.query.QueryWrapperX;
import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 系统用户角色资源库
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 11:44
 */
@Repository
public class SystemUserRoleRepositoryImpl extends ServiceImplX<SystemUserRoleMapper, SystemUserRoleDO> implements SystemUserRoleRepository {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserRelationship(Long userId, List<Role> roles) {
         remove(WrapperX.<SystemUserRoleDO>lambdaQuery().eq(SystemUserRoleDO::getUserId, userId));
         if (CollectionUtils.isEmpty(roles)) {
             return;
         }

        List<SystemUserRoleDO> list = roles.stream().map((a) -> {
            SystemUserRoleDO systemUserRoleDO = new SystemUserRoleDO();
            systemUserRoleDO.setUserId(userId);
            systemUserRoleDO.setRoleId(a.getId());
            return systemUserRoleDO;
        }).toList();

         saveBatch(list);
    }
}
