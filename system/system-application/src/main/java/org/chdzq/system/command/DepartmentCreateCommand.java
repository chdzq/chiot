package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.system.entity.Department;
import org.chdzq.system.repository.DepartmentRepository;

import java.util.Objects;

/**
 * 部门创建命令
 *
 * @author chdzq
 * @create 2025/2/18 18:25
 */
@Data
public class DepartmentCreateCommand implements ICommand<Department, Long> {
    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    private String name;

    /**
     * 部门编号
     */
    @NotBlank(message = "部门编码不能为空")
    private String code;

    /**
     * 父节点id
     */
    private Long parentId = 0L;

    /**
     * 显示顺序
     */
    private Integer sort = 1;

    /**
     * 状态(1:正常;0:禁用)
     */
    @InEnum(value = StatusEnum.class,  message = "状态不能为空")
    private Integer status = StatusEnum.ENABLE.getCode();

    @Override
    public Department buildEntity() {
        return Department.builder()
                .code(code)
                .status(status)
                .sort(sort)
                .name(name)
                .parentId(parentId)
                .build();
    }

    /**
     * 校验
     * @param repository 仓库
     */
    public void validate(DepartmentRepository repository) {
        ValidationUtil.validate(this);
        //说明有code
        if (!Objects.equals(0L, parentId)) {
            Assert.notNull(repository.get(parentId), "部门父节点不存在");
        }
        Assert.isNull(repository.getByCode(code), "部门编码重复");
        Assert.isNull(repository.getByName(name), "部门名称重复");
    }
}
