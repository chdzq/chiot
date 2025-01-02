package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.system.command.*;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.repository.ResourceRepository;
import org.chdzq.system.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 资源服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 09:41
 */
@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(CreateResourceCommand cmd) {
        Resource entity = cmd.toEntity();
        if (
                Objects.isNull(entity.getParentId()) ||
                Objects.equals(entity.getParentId(), 0L)) {
            //说明是顶级节点
            entity.setParentId(0L);
            entity.setPermission(cmd.getCode());
        } else {
            Long parentId = entity.getParentId();
            Assert.isTrue(resourceRepository.isExistByKey(parentId), "父节点不存在");

            Long resourceIdByCode = resourceRepository.getResourceIdByCode(parentId, entity.getCode());
            Assert.isNull(resourceIdByCode, "资源编码已经存在");

            Long resourceIdByName = resourceRepository.getResourceIdByName(parentId, entity.getName());
            Assert.isNull(resourceIdByName, "资源名称已经存在");

            String permission = resourceRepository.getPermissionByKey(parentId);
            entity.setPermission(permission + ":" + cmd.getCode());
        }

        resourceRepository.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UpdateResourceCommand cmd) {
        Long id = cmd.getId();
        Assert.isTrue(resourceRepository.isExistByKey(id), "资源不存在");
        Resource entity = cmd.toEntity();

        Long parentId = resourceRepository.getParentIdByKey(entity.getId());
        entity.setParentId(parentId);

        Assert.isTrue(resourceRepository.isExistByKey(parentId), "父节点不存在");

        Long resourceIdByCode = resourceRepository.getResourceIdByCode(parentId, entity.getCode());
        Assert.isTrue(
                Objects.isNull(resourceIdByCode) || !Objects.equals(id, resourceIdByCode),
                "资源编码已经存在");

        Long resourceIdByName = resourceRepository.getResourceIdByName(parentId, entity.getName());
        Assert.isTrue(
                Objects.isNull(resourceIdByName) || Objects.equals(id, resourceIdByName),
                "资源名称已经存在");

        String permission = resourceRepository.getPermissionByKey(parentId);
        entity.setPermission(permission + ":" + cmd.getCode());

        resourceRepository.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(DeleteResourceCommand cmd) {
        Long id = cmd.getId();
        Boolean exist = resourceRepository.isExistByKey(id);
        Assert.isTrue(exist, "资源不存在");
        resourceRepository.deleteById(id);
    }
}
