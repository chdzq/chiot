package org.chdzq.system.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.system.entity.Department;
import org.chdzq.system.repository.DepartmentRepository;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 部门更新命令
 *
 * @author chdzq
 * @create 2025/2/18 18:25
 */
@Data
public class DepartmentUpdateCommand implements ICommand<Department, Long> {

    /**
     * 部门主键
     */
    @NotNull(message = "部门主键不能为空")
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门编号
     */
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
                .id(id)
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

        Department department = repository.get(id);
        Assert.notNull(department, "当前部门不存在");
        //说明有code
        if (!Objects.equals(0L, parentId)) {
            Assert.notNull(repository.get(parentId), "部门父节点不存在");
        }
        if (StringUtils.hasText(code) && !Objects.equals(code, department.getCode())) {
            Assert.isNull(repository.getByCode(code), "部门编码重复");
        }

        if (StringUtils.hasText(name) && !Objects.equals(name, department.getName())) {
            Assert.isNull(repository.getByName(name), "部门名称重复");
        }
    }
}
