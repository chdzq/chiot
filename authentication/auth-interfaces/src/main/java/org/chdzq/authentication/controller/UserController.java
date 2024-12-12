package org.chdzq.authentication.controller;

import lombok.AllArgsConstructor;
import org.chdzq.authentication.command.CreateUserCommand;
import org.chdzq.authentication.command.DeleteUserCommand;
import org.chdzq.authentication.command.UpdateUserCommand;
import org.chdzq.authentication.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 20:27
 */
@RequestMapping("/api/v1/user")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 新增用户
     * @param user 用户信息
     */
    @PostMapping()
    public void createUser(@RequestBody CreateUserCommand user) {
        userService.createUser(user);
    }

    /**
     * 新增用户
     * @param userId 新增的用户id
     * @param user 用户信息
     */
    @PutMapping(value = "/{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UpdateUserCommand user) {
        user.setId(userId);
        userService.updateUser(user);
    }

    /**
     * 删除用户
     * @param userId 删除的用户id
     */
    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(new DeleteUserCommand(userId));
    }
}
