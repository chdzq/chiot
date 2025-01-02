package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.system.command.CreateRoleCommand;
import org.chdzq.system.command.DeleteRoleCommand;
import org.chdzq.system.command.UpdateRoleCommand;
import org.chdzq.system.convert.SystemApplicationConvertor;
import org.chdzq.system.entity.Role;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateRoleCommand cmd) {
        Long idByCode = roleRepository.getIdByCode(cmd.getCode());
        Assert.isNull(idByCode, "角色编码已存在");

        Long idByName = roleRepository.getIdByName(cmd.getName());
        Assert.isNull(idByName, "角色名称已存在");

        Role role = SystemApplicationConvertor.INSTANCE.roleCreateCommand2Dto(cmd);

        roleRepository.create(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateRoleCommand cmd) {
        Long id = cmd.getId();

        Boolean exist = roleRepository.isExistByKey(id);
        Assert.isTrue(exist, "角色不存在");
        if (StringUtils.hasText(cmd.getCode())) {
            Long idByCode = roleRepository.getIdByCode(cmd.getCode());
            Assert.isTrue(Objects.isNull(idByCode) || Objects.equals(id, idByCode), "角色编码已存在");
        }

        if (StringUtils.hasText(cmd.getName())) {
            Long idByName = roleRepository.getIdByName(cmd.getName());
            Assert.isTrue(Objects.isNull(idByName) || Objects.equals(id, idByName), "角色名称已存在");
        }

        Role role = SystemApplicationConvertor.INSTANCE.roleUpdateCommand2Dto(cmd);

        roleRepository.update(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(DeleteRoleCommand cmd) {
        Long id = cmd.getId();
        Boolean exist = roleRepository.isExistByKey(id);
        Assert.isTrue(exist, "角色不存在");
        roleRepository.deleteById(cmd.getId());
    }
}
