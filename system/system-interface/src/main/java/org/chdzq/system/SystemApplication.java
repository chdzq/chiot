package org.chdzq.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:25
 */
@EnableDiscoveryClient
@SpringBootApplication

public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
