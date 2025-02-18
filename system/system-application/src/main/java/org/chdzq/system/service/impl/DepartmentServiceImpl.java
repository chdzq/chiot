package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.system.command.DepartmentCreateCommand;
import org.chdzq.system.command.DepartmentDeleteCommand;
import org.chdzq.system.command.DepartmentUpdateCommand;
import org.chdzq.system.entity.Department;
import org.chdzq.system.repository.DepartmentRepository;
import org.chdzq.system.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * 部门服务类
 *
 * @author chdzq
 * @create 2025/2/18 18:48
 */
@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository repository;
    @Override
    public void create(DepartmentCreateCommand cmd) {
        cmd.validate(repository);

        Department entity = cmd.buildEntity();

        repository.create(entity);
    }

    @Override
    public void update(DepartmentUpdateCommand cmd) {
        cmd.validate(repository);

        Department entity = cmd.buildEntity();

        repository.update(entity);
    }

    @Override
    public void delete(DepartmentDeleteCommand cmd) {
        cmd.validate(repository);

        Department entity = cmd.buildEntity();

        repository.delete(entity);
    }
}
