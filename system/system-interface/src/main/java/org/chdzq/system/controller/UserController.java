package org.chdzq.system.controller;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.entity.Page;
import org.chdzq.system.command.CreateUserCommand;
import org.chdzq.system.command.DeleteUserCommand;
import org.chdzq.system.command.UpdateUserCommand;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.query.UserPageQuery;
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
    public void createUser(@RequestBody CreateUserCommand cmd) {
        userService.create(cmd);
    }

    /**
     * 更新
     * @param userId 新增的用户id
     * @param cmd 命令
     */
    @PutMapping(value = "/{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UpdateUserCommand cmd) {
        cmd.setId(userId);
        userService.update(cmd);
    }

    /**
     * 删除
     * @param userId 用户id
     */
    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.delete(new DeleteUserCommand(userId));
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
     * 分页获取用户列表
     * @param pageNo 页面 默认为1
     * @param pageSize 分页数量 默认为10
     * @param keyword 搜索关键词
     * @param status 状态
     * @param startTime 搜索开始时间
     * @param endTime 搜索结束时间
     * @return
     */
    @GetMapping(value = "/page")
    public Page<? extends UserVO> page(@RequestParam(name = "pageNo", required = false) Integer pageNo,
                                       @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                       @RequestParam(name = "keyword", required = false) String keyword,
                                       @RequestParam(name = "status", required = false) Integer status,
                                       @RequestParam(name = "startTime", required = false) LocalDateTime startTime,
                                       @RequestParam(name = "endTime", required = false) LocalDateTime endTime) {
        UserPageQuery query = UserPageQuery.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .keyword(keyword)
                .status(status)
                .endTime(endTime)
                .startTime(startTime)
                .build();
        return userQueryService.page(query);
    }
}
