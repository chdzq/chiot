package org.chdzq.system.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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
 * 部门删除命令
 *
 * @author chdzq
 * @create 2025/2/18 18:25
 */
@Data
@AllArgsConstructor
public class DepartmentDeleteCommand implements ICommand<Department, Long> {

    /**
     * 部门主键
     */
    @NotNull(message = "部门主键不能为空")
    private Long id;


    @Override
    public Department buildEntity() {
        return Department.builder()
                .id(id)
                .build();
    }

    /**
     * 校验
     * @param repository 仓库
     */
    public void validate(DepartmentRepository repository) {
        ValidationUtil.validate(this);
    }
}
