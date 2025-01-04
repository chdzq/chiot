package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.ResourceEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.repository.ResourceRepository;
import java.util.Objects;

/**
 * 创建资源命令
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/31 10:56
 */

@Data
public class ResourceCreateCommand implements ICommand<Resource, Long> {

    /**
     * 父菜单ID
     */
    @NotNull(message = "父节点不能为空")
    private Long parentId = 0L;

    /**
     * 菜单名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;


    /**
     * 菜单名称
     */
    @NotBlank(message = "编码不能为空")
    private String code;

    /**
     * 菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)
     */
    @InEnum(ResourceEnum.class)
    private Integer type;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    private String path;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    private String component;

    /**
     * 显示状态(1:显示;0:隐藏)
     */
    @InEnum(StatusEnum.class)
    private Integer enabled;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 校验
     * @param resourceRepository 仓库
     */
    public void validate(ResourceRepository resourceRepository) {
        ValidationUtil.validate(this);

        if (Objects.nonNull(parentId) && !Objects.equals(parentId, 0L)) {
            //说明不是是顶级节点
            Assert.isTrue(resourceRepository.isExistByKey(parentId), "父节点不存在");
        } else {
            parentId = 0L;
        }

        Long resourceIdByCode = resourceRepository.getResourceIdByCode(parentId, code);
        Assert.isNull(resourceIdByCode, "资源编码已经存在");

        Long resourceIdByName = resourceRepository.getResourceIdByName(parentId, name);
        Assert.isNull(resourceIdByName, "资源名称已经存在");
    }

    @Override
    public Resource buildEntity() {
        Resource resource = new Resource();
        resource.setParentId(parentId);
        resource.setName(name);
        resource.setType(ResourceEnum.getByCode(type));
        resource.setPath(path);
        resource.setComponent(component);
        resource.setSort(sort);
        resource.setCode(code);
        resource.setIcon(icon);
        resource.setEnabled(StatusEnum.getByCode(enabled));
        return resource;
    }
}
