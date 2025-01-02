package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.ResourceEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.system.entity.Resource;

/**
 * 更新资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/31 11:18
 */
@Data
public class UpdateResourceCommand implements ICommand {
    /**
     * 资源ID
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 编码
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

    public Resource toEntity() {
        Resource resource = new Resource();
        resource.setName(name);
        resource.setCode(code);
        resource.setType(ResourceEnum.getByCode(type));
        resource.setPath(path);
        resource.setComponent(component);
        resource.setSort(sort);
        resource.setIcon(icon);
        resource.setEnabled(StatusEnum.getByCode(enabled));
        return resource;
    }
}
