package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Value;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.entity.Role;
import org.chdzq.system.repository.ResourceRepository;
import org.chdzq.system.repository.RoleRepository;

/**
 * 删除资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/31 11:19
 */
@Value
public class DeleteResourceCommand implements ICommand<Resource, Long> {

    /**
     * 用户Id
     */
    Long id;

    public DeleteResourceCommand(
            @NotBlank(message = "主键不能为空") Long id)
    {
        this.id = id;
    }

    /**
     * 校验
     * @param resourceRepository
     */
    public void validate(ResourceRepository resourceRepository) {
        ValidationUtil.validate(this);
        Boolean exist = resourceRepository.isExistByKey(id);
        Assert.isTrue(exist, "资源不存在");
    }

    @Override
    public Resource buildEntity() {
        return new Resource(id);
    }
}
