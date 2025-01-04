package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.system.entity.User;
import org.chdzq.system.repository.UserRepository;

/**
 * 删除用户命令
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 20:04
 */
@Data
public class UserDeleteCommand implements ICommand<User, Long> {

    /**
     * 用户Id
     */
    @NotBlank(message = "主键不能为空")
    private Long id;

    public UserDeleteCommand(Long id) {
        this.id = id;
    }

    /**
     * 校验
     * @param userRepository
     */
    public void validate(UserRepository userRepository) {
        ValidationUtil.validate(this);
        Boolean exist = userRepository.isExistByKey(id);
        Assert.isTrue(exist, "用户不存在");
    }

    @Override
    public User buildEntity() {
        User user = new User();
        user.setId(id);
        return user;
    }
}
