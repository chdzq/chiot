package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.enums.ResourceEnum;
import org.chdzq.system.command.ResourceCreateCommand;
import org.chdzq.system.command.ResourceDeleteCommand;
import org.chdzq.system.command.ResourceUpdateCommand;
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
    public void create(ResourceCreateCommand cmd) {
        cmd.validate(resourceRepository);
        Resource entity = cmd.buildEntity();
        resourceRepository.create(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ResourceUpdateCommand cmd) {
        cmd.validate(resourceRepository);

        Resource entity = cmd.buildEntity();

        resourceRepository.update(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ResourceDeleteCommand cmd) {
        cmd.validate(resourceRepository);
        Resource resource = cmd.buildEntity();
        resourceRepository.delete(resource);
    }
}
