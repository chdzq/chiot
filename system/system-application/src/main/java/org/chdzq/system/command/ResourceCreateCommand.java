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
     * 重定向地址 外链地址
     */
    private String link;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    private String component;

    /**
     * 显示状态(1:显示;0:隐藏)
     */
    @InEnum(StatusEnum.class)
    private Integer enabled = StatusEnum.ENABLE.getCode();

    /**
     * 排序
     */
    private Integer sort = 0;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 权限
     */
    private String permission;


    /**
     * 校验
     * @param resourceRepository 仓库
     */
    public void validate(ResourceRepository resourceRepository) {
        ValidationUtil.validate(this);

        if (!Objects.equals(type, ResourceEnum.BUTTON.getCode())) {
            Assert.isTrue(StringUtils.hasText(path), "path不能为空");
        }

        if (Objects.equals(type, ResourceEnum.PAGE.getCode())) {
            Assert.isTrue(StringUtils.hasText(component), "component不能为空");
        }

        if (Objects.equals(type, ResourceEnum.LINK.getCode())) {
            Assert.isTrue(StringUtils.hasText(link), "外链地址不能为空");
        }

        if (Objects.nonNull(parentId) && !Objects.equals(parentId, 0L)) {
            //说明不是是顶级节点
            Resource parent = resourceRepository.getBy(parentId);
            Assert.notNull(parent, "父节点不存在");
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
        Resource.ResourceBuilder builder = Resource.builder();
        if (!Objects.equals(type, ResourceEnum.BUTTON.getCode())) {
            //目录需要是/开头
            if (!StringUtils.startsWithIgnoreCase(path, "/")) {
                builder.path("/" + path);
            } else {
                builder.path(path);
            }
        }
        builder.type(type)
                .link(link)
                .parentId(parentId)
                .component(component)
                .permission(permission)
                .icon(icon)
                .sort(sort)
                .code(code)
                .name(name)
                .enabled(enabled);
        return builder.build();
    }
}
