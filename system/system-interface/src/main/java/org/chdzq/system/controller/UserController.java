package org.chdzq.system.controller;

import org.chdzq.system.entity.UserAuthInfo;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:39
 */
@RestController()
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户认证信息
     * @param username 用户名
     * @return 认证信息
     */
    @GetMapping("/{username}/authInfo")
    public UserAuthInfo getUserAuthInfo(@PathVariable String username) {
        QueryAuthInfo query = new QueryAuthInfo(username);
        UserAuthInfo userAuthInfo = userService.query(query);
        return userAuthInfo;
    }
}
