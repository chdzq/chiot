package org.chdzq.system.external;

import org.chdzq.system.entity.AuthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户对外暴露的接口
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/13 00:37
 */
@FeignClient(value= "chiot-system")
public interface UserFeignClient {

    /**
     * 获取用户认证信息
     * @param username 用户名
     */
    @GetMapping("/api/v1/user/{username}/authInfo")
    AuthInfo getUserAuthInfo(@PathVariable("username") String username);
}
