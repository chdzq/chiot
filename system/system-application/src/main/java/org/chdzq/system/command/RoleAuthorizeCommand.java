package org.chdzq.system.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.entity.Role;
import org.chdzq.system.repository.ResourceRepository;
import org.chdzq.system.repository.RoleRepository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色授权命令
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/3 09:24
 */
@Data
@Builder
public class RoleAuthorizeCommand implements ICommand<Role, Long> {

    /**
     * 角色名称
     */
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    /**
     * 资源列表
     */
    @NotNull(message = "资源列表不能为空")
    private List<Long> resourceIds;

    /**
     * @ignore
     * 资源列表
     */
    @JsonIgnore
    private List<Resource> resources;


    /**
     * 校验
     * @param roleRepository 角色仓库
     * @param resourceRepository 资源仓库
     */
    public void validate(RoleRepository roleRepository, ResourceRepository resourceRepository) {
        ValidationUtil.validate(this);

        Boolean exist = roleRepository.isExistByKey(roleId);

        Assert.isTrue(exist, "角色不存在");

        if (!CollectionUtils.isEmpty(resourceIds)) {
            List<Resource> resources = resourceRepository.getResources(resourceIds);
            resources.forEach((resource) -> {
                Assert.isTrue(resourceIds.contains(resource.getId()), String.format("资源:%s不存在", resource.getCode()));
            });
            this.resources = resources;
        } else {
            resources = new ArrayList<>();
        }
    }

    @Override
    public Role buildEntity() {
        return Role.builder()
                .id(roleId)
                .resource(resources)
                .build();
    }
}
