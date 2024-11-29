package org.chdzq.system.fegin;

import org.chdzq.system.constrants.SystemServiceName;
import org.chdzq.system.entity.UserAuthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = SystemServiceName.SERVICE_NAME)
public interface UserFeignClient {

    @GetMapping("/v1/user/{username}/authInfo")
    UserAuthInfo getUserAuthInfo(@PathVariable("username") String username);
}
