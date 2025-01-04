package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.system.command.CreateRoleCommand;
import org.chdzq.system.command.DeleteRoleCommand;
import org.chdzq.system.command.RoleAuthorizeCommand;
import org.chdzq.system.command.UpdateRoleCommand;
import org.chdzq.system.convert.SystemApplicationConvertor;
import org.chdzq.system.entity.Role;
import org.chdzq.system.repository.ResourceRepository;
import org.chdzq.system.repository.RoleRepository;
import org.chdzq.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 角色服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 16:58
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    private ResourceRepository resourceRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateRoleCommand cmd) {
        cmd.validate(roleRepository);

        Role role = cmd.buildEntity();

        roleRepository.create(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateRoleCommand cmd) {

        cmd.validate(roleRepository);

        Role role = cmd.buildEntity();

        roleRepository.update(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(DeleteRoleCommand cmd) {
        cmd.validate(roleRepository);
        Role role = cmd.buildEntity();
        roleRepository.delete(role);
    }

    @Override
    public void authorize(RoleAuthorizeCommand command) {
        //1 校验
        command.validate(roleRepository, resourceRepository);

        //2.更新
        Role role = command.buildEntity();
        roleRepository.update(role);
    }
}
