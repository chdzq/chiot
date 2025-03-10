package org.chdzq.system.controller;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.entity.Page;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.security.utils.UserContext;
import org.chdzq.system.command.UserCreateCommand;
import org.chdzq.system.command.UserDeleteCommand;
import org.chdzq.system.command.UserUpdateCommand;
import org.chdzq.system.command.UserUpdateProfileCommand;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.query.UserPageQuery;
import org.chdzq.system.query.model.UserInfo;
import org.chdzq.system.query.model.UserPageVO;
import org.chdzq.system.query.model.UserProfileVO;
import org.chdzq.system.query.model.UserVO;
import org.chdzq.system.service.UserQueryService;
import org.chdzq.system.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    private final UserQueryService userQueryService;

    /**
     * 新增用户
     * @param cmd 命令
     */
    @PostMapping()
    public void createUser(@RequestBody UserCreateCommand cmd) {
        userService.create(cmd);
    }

    /**
     * 更新 当前用户简介
     * @param cmd 命令
     */
    @PutMapping(value = "/profile")
    public void updateUser( @RequestBody UserUpdateProfileCommand cmd) {
        cmd.setId(UserContext.getUserId());
        userService.update(cmd);
    }

    /**
     * 更新
     * @param userId 新增的用户id
     * @param cmd 命令
     */
    @PutMapping(value = "/{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UserUpdateCommand cmd) {
        cmd.setId(userId);
        userService.update(cmd);
    }

    /**
     * 删除
     * @param userId 用户id
     */
    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.delete(new UserDeleteCommand(userId));
    }

    /**
     * 获取用户认证信息
     * @param username 用户名
     */
    @GetMapping(value = "/{username}/authInfo")
    public AuthInfo updateUser(@PathVariable("username") String username) {
        return userService.getAuthInfo(new QueryAuthInfo(username));
    }

    /**
     * 查询当前用户的登录信息
     * @return 登录信息
     */
    @GetMapping(value = "/info")
    public UserInfo userInfo() {
        return userQueryService.getCurrentUserInfo();
    }

    /**
     * 查询用户信息
     * @param userId 新增的用户id
     */
    @GetMapping(value = "/{userId}")
    public UserVO detail(
            @PathVariable("userId") Long userId) {
        return userQueryService.detail(userId);
    }

    @GetMapping("/page")
    public Page<? extends UserPageVO> page(
            @RequestParam(name = "pageNo", required = false) Integer pageNo,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "departmentId", required = false) Long departmentId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "status", required = false)  Integer status,
            @RequestParam(value = "startTime", required = false)  LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false)  LocalDateTime endTime
    ) {
        UserPageQuery pageQuery = UserPageQuery.builder()
                .keyword(keyword)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .status(status)
                .departmentId(departmentId)
                .endTime(endTime)
                .startTime(startTime)
                .build();
        return userQueryService.page(pageQuery);
    }

    /**
     * 获取用户个人中心信息
     * @return
     */
    @GetMapping("/profile")
    public UserProfileVO getUserProfile() {
        return userQueryService.getUserProfile(UserContext.getUserId());
    }

}
