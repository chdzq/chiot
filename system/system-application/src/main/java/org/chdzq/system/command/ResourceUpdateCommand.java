package org.chdzq.system.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 更新资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/31 11:18
 */
@Data
public class ResourceUpdateCommand implements ICommand<Resource, Long> {
    /**
     * 资源ID
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 父资源ID
     */
    @NotNull(message = "主键不能为空")
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
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
     * 权限标识
     */
    private String permission;

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
     * 重定向地址 外链地址
     */
    private String link;


    /**
     * 校验
     * @param resourceRepository 仓库
     */
    public void validate(ResourceRepository resourceRepository) {
        ValidationUtil.validate(this);

        Assert.notNull(resourceRepository.get(id), "资源不存在");

        if (Objects.nonNull(parentId) && !Objects.equals(parentId, 0L)) {
            //说明不是是顶级节点
            Resource parent = resourceRepository.get(parentId);
            Assert.notNull(parent, "父节点不存在");
        }

        Resource resourceByCode = resourceRepository.getResourceInParentByCode(parentId, code);
        Assert.isTrue(
                Objects.isNull(resourceByCode) || Objects.equals(id, resourceByCode.getId()),
                "资源编码已经存在");

        Resource resourceByName = resourceRepository.getResourceInParentByName(parentId, name);
        Assert.isTrue(
                Objects.isNull(resourceByName) || Objects.equals(id, resourceByName.getId()),
                "资源名称已经存在");
    }

    @Override
    public Resource buildEntity() {
        Resource.ResourceBuilder builder = Resource.builder()
                .id(id)
                .type(type)
                .link(link)
                .parentId(parentId)
                .component(component)
                .permission(permission)
                .icon(icon)
                .sort(sort)
                .code(code)
                .name(name)
                .enabled(enabled);
        if (StringUtils.hasText(path)) {
            //目录需要是/开头
            if (!StringUtils.startsWithIgnoreCase(path, "/")) {
                builder.path("/" + path);
            } else {
                builder.path(path);
            }
        }
        return builder.build();
    }
}
